package com.wanghuiwen.base.vo;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class RoleApiAdd {
    private long roleId;
    private List<Long> api;

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public List<Long> getApi() {
        return api;
    }

    public void setApi(List<Long> api) {
        this.api = api;
    }
}
