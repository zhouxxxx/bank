package com.thinkgem.jeesite.modules.bank.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.math.BigDecimal;

public class FinanceDetail extends DataEntity<FinanceDetail>{
    private String serialNo;
    private String userId;
    private BigDecimal money;
    private BigDecimal ckMoney;
    private BigDecimal qkMoney;
    private BigDecimal dkMoney;
    private String applyTime;
    private String userName;
    private String bankId;
    private String phone;
    private String sex;
    private String age;
    private String type;
    private String inOut;
    private String loginName;
    private String startTime;
    private String endTime;
    private String jsAccountId;
    private String moneyStr;

    public BigDecimal getDkMoney() {
        return dkMoney;
    }

    public void setDkMoney(BigDecimal dkMoney) {
        this.dkMoney = dkMoney;
    }

    public BigDecimal getCkMoney() {
        return ckMoney;
    }

    public void setCkMoney(BigDecimal ckMoney) {
        this.ckMoney = ckMoney;
    }

    public BigDecimal getQkMoney() {
        return qkMoney;
    }

    public void setQkMoney(BigDecimal qkMoney) {
        this.qkMoney = qkMoney;
    }

    public String getMoneyStr() {
        return moneyStr;
    }

    public void setMoneyStr(String moneyStr) {
        this.moneyStr = moneyStr;
    }

    public String getJsAccountId() {
        return jsAccountId;
    }

    public void setJsAccountId(String jsAccountId) {
        this.jsAccountId = jsAccountId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInOut() {
        return inOut;
    }

    public void setInOut(String inOut) {
        this.inOut = inOut;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }
}
