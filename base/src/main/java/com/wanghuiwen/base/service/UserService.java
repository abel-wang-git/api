package com.wanghuiwen.base.service;
import com.wanghuiwen.base.model.Api;
import com.wanghuiwen.base.model.Button;
import com.wanghuiwen.base.model.Menu;
import com.wanghuiwen.base.model.User;
import com.wanghuiwen.core.config.AuthUser;
import com.wanghuiwen.core.service.Service;
import com.wanghuiwen.core.response.Result;

import java.util.List;
import java.util.Map;

/**
 * Created by wanghuiwen on 2021/07/13.
 */
public interface UserService extends Service<User> {
    List<User> list(Map<String, Object> params);

    Result addRole(List<Long> roles, Long userId, AuthUser authUser);

    Result login(AuthUser userDetails);

    /**
     * 获取用户权限
     * @param id 用户id
     * @return
     */
    List<Api> getApis(Long id);



    User findByLoginName(String s);

    /**
     * 获取用户菜单
     * @param id 用户id
     * @return
     */
    List<Menu> getMenus(Long id);

    /**
     * 获取部门下的人员
     * @param id
     * @return
     */
    List<User> getByDepartment(Long id);

    List<Button> getButtons(Long id);


    /**
     * 注册
     */
    void register(User user,String roleName);
}
