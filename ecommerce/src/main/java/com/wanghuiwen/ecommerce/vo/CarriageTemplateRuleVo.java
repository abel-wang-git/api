package com.wanghuiwen.ecommerce.vo;

import com.wanghuiwen.address.vo.CityVo;

import java.math.BigDecimal;
import java.util.List;

public class CarriageTemplateRuleVo {
    private Long ruleId;

    private Long templateId;

    /**
     * 运费
     */
    private BigDecimal minCarriage;

    /**
     * 首件
     */
    private Integer minNum;

    /**
     * 续件
     */
    private Integer ladder;

    /**
     * 运费
     */
    private BigDecimal ladderPrice;

    List<CityVo> cities;


    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
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

    public List<CityVo> getCities() {
        return cities;
    }

    public void setCities(List<CityVo> cities) {
        this.cities = cities;
    }
}
