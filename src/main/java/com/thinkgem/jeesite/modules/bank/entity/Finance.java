package com.thinkgem.jeesite.modules.bank.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.math.BigDecimal;

public class Finance extends DataEntity<Finance>{
    private String personId;
    private String userName;
    private String bankId;
    private String phone;
    private String sex;
    private String age;
    private String type;
    private String startTime;
    private String endTime;
    private String loginName;
    private BigDecimal money;
    private String dkType;
    private String time;
    private BigDecimal dkMoney;
    private BigDecimal hkMoney;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public BigDecimal getDkMoney() {
        return dkMoney;
    }

    public void setDkMoney(BigDecimal dkMoney) {
        this.dkMoney = dkMoney;
    }

    public BigDecimal getHkMoney() {
        return hkMoney;
    }

    public void setHkMoney(BigDecimal hkMoney) {
        this.hkMoney = hkMoney;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDkType() {
        return dkType;
    }

    public void setDkType(String dkType) {
        this.dkType = dkType;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
