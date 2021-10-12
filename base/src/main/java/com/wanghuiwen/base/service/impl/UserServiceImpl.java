package com.wanghuiwen.base.service.impl;

import com.wanghuiwen.base.config.ProjectConstant;
import com.wanghuiwen.base.config.auth.JwtTokenUtil;
import com.wanghuiwen.base.dao.SysDepartmentMapper;
import com.wanghuiwen.base.dao.UserMapper;
import com.wanghuiwen.base.dao.UserRoleMapper;
import com.wanghuiwen.base.model.*;
import com.wanghuiwen.base.service.*;
import com.wanghuiwen.core.config.AuthUser;
import com.wanghuiwen.core.response.Result;
import com.wanghuiwen.core.response.ResultEnum;
import com.wanghuiwen.core.response.ResultGenerator;
import com.wanghuiwen.core.service.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;


/**
 * Created by wanghuiwen on 2021/07/13.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private ResultGenerator resultGenerator;
    @Resource
    private RoleService roleService;

    @Resource
    private ApiService apiService;

    @Resource
    private MenuService menuService;

    @Resource
    private SysDepartmentMapper sysDepartmentMapper;
    @Resource
    private ButtonService buttonService;


    @Override
    public List<User> list(Map<String, Object> params) {
        return userMapper.list(params);
    }

    @Override
    @CacheEvict(value="User::Role",key = "#userId")
    public Result addRole(List<Long> roles, Long userId, AuthUser authUser) {
        User user = findById(userId);
        if (user == null) return resultGenerator.genFailResult(ResultEnum.NO_RELATED_USER);

        List<UserRole> userRoles = new ArrayList<>();
        for (Long id : roles) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(id);
            userRoles.add(userRole);
        }

        /**
         * 如果不是管理员不能操作系统角色 dept_id  = 1
         */
        if(authUser.getRoles().contains(ProjectConstant.ROLE_ADMIN)){
            userMapper.deleteRoleById(userId);
        }else {
            userMapper.deleteRoleByIdNoAdmin(userId);
        }

        userRoleMapper.insertListNoAuto(userRoles);
        return resultGenerator.genSuccessResult();
    }

    @Override
    public Result login(AuthUser userDetails) {
        Map<String, Object> res = new HashMap<>();

        String jwtToken = jwtTokenUtil.generateToken(userDetails.getUsername());
        userDetails.setPassword("");
        res.put("role",userDetails.getRoles());
        res.put("token", jwtToken);
        res.put("id", userDetails.getId());
        res.put("nickname", userDetails.getNickName());
        res.put("avatar", userDetails.getAvatar());
        res.put("username", userDetails.getUsername());

        /**
         * 设置登陆过期时间
         */

        User user = new User();
        user.setId(userDetails.getId());
        user.setCredentialsExpiredTime(jwtTokenUtil.getExpiredDate(jwtToken).getTime());
        update(user);

        return resultGenerator.genSuccessResult(ResultEnum.LOGIN_SUCCESS,res);
    }

    @Override
    public List<Api> getApis(Long id) {
        List<Api> apis = new ArrayList<>();
        List<Role> roles = roleService.getByUser(id);
        for (Role role : roles) {
            List<Api> roleApi = apiService.getByRole(role.getId());
            apis.addAll(roleApi);
        }
        return apis;
    }
    @Override
    @Cacheable(value="User",key = "#s")
    public User findByLoginName(String s) {
        return findBy("loginName",s);
    }

    @Override
    public List<Menu> getMenus(Long id) {
        List<Menu> menus = new ArrayList<>();
        List<Role> roles = roleService.getByUser(id);
        for (Role role : roles) {
            List<Menu> roleApi = menuService.getByRole(role.getId());
            menus.addAll(roleApi);
        }
        //多个角色有重复的菜单 去重
        menus =  menus.stream().distinct().collect(collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(Menu::getId))), ArrayList::new));
        return menus;
    }

    @Override
    public List<User> getByDepartment(Long id) {
        SysDepartment department = sysDepartmentMapper.selectByPrimaryKey(id);
        List<SysDepartment> departments =  getChildDepartment(Collections.singletonList(id));
        departments.add(department);

        return userMapper.getByDepartment(departments.stream().map(SysDepartment::getId).collect(Collectors.toList()));
    }


    List<SysDepartment>  getChildDepartment(List<Long> id){
        List<SysDepartment> sysDepartments = new ArrayList<>();
        for (Long aLong : id) {
            List<SysDepartment> departments = sysDepartmentMapper.getChild(aLong);
            sysDepartments.addAll(departments);
            if(!CollectionUtils.isEmpty(departments)){
                List<Long> ids = departments.stream().map(SysDepartment::getId).distinct().collect(Collectors.toList());
                sysDepartments.addAll(getChildDepartment(ids));
            }
        }
        return sysDepartments;
    }


    @Override
    public List<Button> getButtons(Long id) {
        List<Button>  buttons = new ArrayList<>();
        List<Role> roles = roleService.getByUser(id);
        for (Role role : roles) {
            List<Button> roleApi = buttonService.getByRole(role.getId());
            buttons.addAll(roleApi);
        }
        //多个角色有重复的按钮 去重
        buttons =  buttons.stream().distinct().collect(collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(Button::getButtonId))), ArrayList::new));
        return buttons;
    }

    @Override
    public void register(User user,String roleName) {
        User oldUser = findByLoginName(user.getLoginName());
        if(oldUser!=null){
            logger.debug(user.getLoginName()+"用户已经注册");
        }else {
            save(user);
            Role role = roleService.findBy("name", roleName);
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(role.getId());
            userRoleMapper.insertSelective(userRole);
        }
    }
}
