package com.wanghuiwen.ecommerce.vo;

import com.wanghuiwen.address.vo.CityVo;

import java.math.BigDecimal;
import java.util.List;

public class CarriageFreeRuleVo {

    private Long carriageFreeId;

    /**
     * 关联模版
     */
    private Long templateId;

    /**
     * 件数
     */
    private Integer quantity;

    /**
     * 金额
     */
    private BigDecimal amount;

    List<CityVo> cities;

    public Long getCarriageFreeId() {
        return carriageFreeId;
    }

    public void setCarriageFreeId(Long carriageFreeId) {
        this.carriageFreeId = carriageFreeId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<CityVo> getCities() {
        return cities;
    }

    public void setCities(List<CityVo> cities) {
        this.cities = cities;
    }
}
