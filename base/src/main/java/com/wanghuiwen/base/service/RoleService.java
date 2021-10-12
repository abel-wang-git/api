package com.wanghuiwen.base.service;
import com.wanghuiwen.base.model.Role;
import com.wanghuiwen.base.vo.RoleApiAdd;
import com.wanghuiwen.core.service.Service;
import com.wanghuiwen.core.response.Result;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wanghuiwen on 2021/07/13.
 */
public interface RoleService extends Service<Role> {
    /**
     * 获取用户角色
     * @param id
     * @return
     */
    List<Role> getByUser(Long id);

    /**
     * 角色修改权限
     * @param add
     * @return
     */
    Result addApi(RoleApiAdd add);

    /**
     * 角色修改菜单
     * @param add
     * @return
     */
    Result addMenu(RoleApiAdd add);

    /**
     * 角色保存按钮
     * @param add
     * @return
     */
    Result addButton(RoleApiAdd add);
}
