package com.wanghuiwen.ecommerce.vo;

import java.math.BigDecimal;
import java.util.List;

public class CarriageTemplateVo {
    private Long templateId;

    /**
     * 模版名称
     */
    private String templateName;

    /**
     * 计费方式 1 重量 2 数量
     */
    private Integer carriageMethod;

    /**
     * 商户ID
     */
    private Long merchantId;

    private Boolean freeAll;

    /**
     * 首件运费
     */
    private BigDecimal minCarriage;

    /**
     * 几件起算
     */
    private Integer minNum;

    /**
     * 续件数量
     */
    private Integer ladder;

    /**
     * 续件价格
     */
    private BigDecimal ladderPrice;

    /**
     * 运费规则
     */
    List<CarriageTemplateRuleVo> carriageRules;

    /**
     * 包邮规则
     */
    List<CarriageFreeRuleVo> freeRules;

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Integer getCarriageMethod() {
        return carriageMethod;
    }

    public void setCarriageMethod(Integer carriageMethod) {
        this.carriageMethod = carriageMethod;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public BigDecimal getMinCarriage() {
        return minCarriage;
    }

    public void setMinCarriage(BigDecimal minCarriage) {
        this.minCarriage = minCarriage;
    }

    public Integer getMinNum() {
        return minNum;
    }

    public void setMinNum(Integer minNum) {
        this.minNum = minNum;
    }

    public Integer getLadder() {
        return ladder;
    }

    public void setLadder(Integer ladder) {
        this.ladder = ladder;
    }

    public BigDecimal getLadderPrice() {
        return ladderPrice;
    }

    public void setLadderPrice(BigDecimal ladderPrice) {
        this.ladderPrice = ladderPrice;
    }

    public List<CarriageTemplateRuleVo> getCarriageRules() {
        return carriageRules;
    }

    public void setCarriageRules(List<CarriageTemplateRuleVo> carriageRules) {
        this.carriageRules = carriageRules;
    }

    public List<CarriageFreeRuleVo> getFreeRules() {
        return freeRules;
    }

    public void setFreeRules(List<CarriageFreeRuleVo> freeRules) {
        this.freeRules = freeRules;
    }

    public Boolean getFreeAll() {
        return freeAll;
    }

    public void setFreeAll(Boolean freeAll) {
        this.freeAll = freeAll;
    }
}
