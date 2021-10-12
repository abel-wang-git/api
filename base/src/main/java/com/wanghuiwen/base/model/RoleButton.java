package com.wanghuiwen.base.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "role_button")
public class RoleButton implements Serializable {
    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "button_id")
    private Long buttonId;

    private static final long serialVersionUID = 1L;

    /**
     * @return role_id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * @return button_id
     */
    public Long getButtonId() {
        return buttonId;
    }

    /**
     * @param buttonId
     */
    public void setButtonId(Long buttonId) {
        this.buttonId = buttonId;
    }
}