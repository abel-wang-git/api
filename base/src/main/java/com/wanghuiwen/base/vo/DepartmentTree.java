package com.wanghuiwen.base.vo;

import java.util.List;

public class DepartmentTree {
    private Long id;
    private Long pid;
    private Long adminId;

    private String name;
    private String ekey;
    /**
     * 1 部门
     * 2 角色
     */
    private Integer type;

    private List<DepartmentTree> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DepartmentTree> getChildren() {
        return children;
    }

    public void setChildren(List<DepartmentTree> children) {
        this.children = children;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getEkey() {
        return ekey;
    }

    public void setEkey(String ekey) {
        this.ekey = ekey;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
}
