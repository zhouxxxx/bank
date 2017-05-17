/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bank.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bank.entity.Finance;
import com.thinkgem.jeesite.modules.bank.entity.FinanceDetail;
import com.thinkgem.jeesite.modules.bank.entity.Rate;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.math.BigDecimal;
import java.util.List;


@MyBatisDao
public interface FinanceManagementDao extends CrudDao<Finance> {


    List<FinanceDetail> findDeatil(Finance finance);

    void saveMoney(FinanceDetail financeDetail);

    void saveGlide(FinanceDetail financeDetail);

    BigDecimal findMoney(FinanceDetail financeDetail);

    void saveBankMoney(FinanceDetail financeDetail);

    void saveDkMoney(FinanceDetail financeDetail);

    Finance findDkHkMoney(Finance finance);

    void restDkMoney(FinanceDetail financeDetail);

    List<FinanceDetail> findMonthDetailfinance(Finance finance);

    Rate getNowRate();

    void insertRate(Rate rate);

    void updateRate(Rate rate);

    List<FinanceDetail> findDeatilList(Finance finance);

    void updatePassword(User user);

    void insertPerson(FinanceDetail finance);

    User findBankId(String bankId);

    String findJsAccount(FinanceDetail finance);

    void saveToperson1(FinanceDetail finance);

    void saveToperson2(FinanceDetail finance);
}
