package com.wanghuiwen.base.service.impl;

import com.wanghuiwen.base.dao.RoleApiMapper;
import com.wanghuiwen.base.dao.RoleButtonMapper;
import com.wanghuiwen.base.dao.RoleMapper;
import com.wanghuiwen.base.dao.RoleMenuMapper;
import com.wanghuiwen.base.model.*;
import com.wanghuiwen.base.service.RoleService;
import com.wanghuiwen.base.vo.RoleApiAdd;
import com.wanghuiwen.core.response.Result;
import com.wanghuiwen.core.response.ResultEnum;
import com.wanghuiwen.core.response.ResultGenerator;
import com.wanghuiwen.core.response.ResultMessage;
import com.wanghuiwen.core.service.AbstractService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by wanghuiwen on 2021/07/13.
 */
@Service
@Transactional
public class RoleServiceImpl extends AbstractService<Role> implements RoleService {

    @Resource
    private ResultGenerator resultGenerator;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleApiMapper roleApiMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private RoleButtonMapper roleButtonMapper;

    @Override
    @Cacheable(value="User::Role",key = "#id")
    public List<Role> getByUser(Long id) {
        return roleMapper.getByUser(id);
    }

    @Override
    @CacheEvict(value="Role::Api",key = "#add.roleId")
    public Result addApi(RoleApiAdd add) {

        Role role = findById(add.getRoleId());
        if (role == null) return resultGenerator.genResult(ResultEnum.NO_RELATED_USER);

        List<RoleApi> roleApis = new ArrayList<>();
        for (Long id : add.getApi()) {
            RoleApi roleApi = new RoleApi();
            roleApi.setApiId(id);
            roleApi.setRoleId(add.getRoleId());
            roleApis.add(roleApi);
        }

        roleMapper.deleteApiById(add.getRoleId());
        if(!CollectionUtils.isEmpty(roleApis)){
            roleApiMapper.insertListNoAuto(roleApis);
        }

        return resultGenerator.genSuccessResult();
    }

    @Override
    @CacheEvict(value = "Role::Menu",key = "#add.roleId")
    public Result addMenu(RoleApiAdd add) {

        Role role = findById(add.getRoleId());

        if (role == null) return resultGenerator.genFailResult(ResultEnum.NO_RELATED_USER);

        List<RoleMenu> roleMenus = new ArrayList<>();
        for (Long id : add.getApi()) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(id);
            roleMenu.setRoleId(add.getRoleId());
            roleMenus.add(roleMenu);
        }

        roleMapper.deleteMenuById(add.getRoleId());
        if(!CollectionUtils.isEmpty(roleMenus)){
            roleMenuMapper.insertListNoAuto(roleMenus);
        }

        return resultGenerator.genSuccessResult();
    }

    @Override
    @CacheEvict(value = "Role::Button",key = "#add.roleId")
    public Result addButton(RoleApiAdd add) {

        Role role = findById(add.getRoleId());

        if (role == null) return resultGenerator.genFailResult(ResultEnum.NO_RELATED_USER);

        List<RoleButton> roleButtons = new ArrayList<>();
        for (Long id : add.getApi()) {
            RoleButton roleButton = new RoleButton();
            roleButton.setButtonId(id);
            roleButton.setRoleId(add.getRoleId());
            roleButtons.add(roleButton);
        }

        roleMapper.deleteButtonByRole(add.getRoleId());
        if(!CollectionUtils.isEmpty(roleButtons)){
            roleButtonMapper.insertListNoAuto(roleButtons);
        }
        return resultGenerator.genSuccessResult();
    }
}
