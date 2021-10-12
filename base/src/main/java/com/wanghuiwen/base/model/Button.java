package com.wanghuiwen.base.model;

import java.io.Serializable;
import javax.persistence.*;

public class Button implements Serializable {
    @Id
    @Column(name = "button_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer buttonId;

    /**
     * 按钮名称
     */
    private String name;

    /**
     * 模块名称
     */
    @Column(name = "mode_name")
    private String modeName;

    /**
     * 按钮权限
     */
    @Column(name = "button_code")
    private String buttonCode;

    private static final long serialVersionUID = 1L;

    /**
     * @return button_id
     */
    public Integer getButtonId() {
        return buttonId;
    }

    /**
     * @param buttonId
     */
    public void setButtonId(Integer buttonId) {
        this.buttonId = buttonId;
    }

    /**
     * 获取按钮名称
     *
     * @return name - 按钮名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置按钮名称
     *
     * @param name 按钮名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取模块名称
     *
     * @return mode_name - 模块名称
     */
    public String getModeName() {
        return modeName;
    }

    /**
     * 设置模块名称
     *
     * @param modeName 模块名称
     */
    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    /**
     * 获取按钮权限
     *
     * @return button_code - 按钮权限
     */
    public String getButtonCode() {
        return buttonCode;
    }

    /**
     * 设置按钮权限
     *
     * @param buttonCode 按钮权限
     */
    public void setButtonCode(String buttonCode) {
        this.buttonCode = buttonCode;
    }
}