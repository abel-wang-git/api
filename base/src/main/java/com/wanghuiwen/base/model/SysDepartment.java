package com.wanghuiwen.base.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_department")
public class SysDepartment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long pid;

    /**
     * 管理员Id
     */
    @Column(name = "admin_id")
    private Long adminId;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return pid
     */
    public Long getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取管理员Id
     *
     * @return admin_id - 管理员Id
     */
    public Long getAdminId() {
        return adminId;
    }

    /**
     * 设置管理员Id
     *
     * @param adminId 管理员Id
     */
    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
}