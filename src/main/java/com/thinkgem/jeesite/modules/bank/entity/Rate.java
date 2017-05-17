package com.thinkgem.jeesite.modules.bank.entity;


import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.math.BigDecimal;

public class Rate extends DataEntity<Rate>{
    private String type ;
    private BigDecimal ckRate;
    private BigDecimal dkRate;

    public BigDecimal getCkRate() {
        return ckRate;
    }

    public void setCkRate(BigDecimal ckRate) {
        this.ckRate = ckRate;
    }

    public BigDecimal getDkRate() {
        return dkRate;
    }

    public void setDkRate(BigDecimal dkRate) {
        this.dkRate = dkRate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
