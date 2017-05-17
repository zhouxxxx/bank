/**
 * Copyright &copy; 2012-2013 <a href="httparamMap://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bank.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.bank.dao.FinanceManagementDao;
import com.thinkgem.jeesite.modules.bank.entity.Finance;
import com.thinkgem.jeesite.modules.bank.entity.FinanceDetail;
import com.thinkgem.jeesite.modules.bank.entity.Rate;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
@Transactional(readOnly = true)
public class FinanceManagementService extends CrudService<FinanceManagementDao, Finance> {

	@Autowired
	private FinanceManagementDao financeManagementDao;

	public Finance get(String id) {
          return super.get(id);
	}

	public Page<Finance> findDateList(Page<Finance> userPage, Finance finance) {
		return super.findPage(userPage,finance);
	}

	public Page<FinanceDetail> findDeatil(Page<FinanceDetail> userPage, Finance finance) {
		FinanceDetail financeDetail = new FinanceDetail();
		financeDetail.setPage(userPage);
		userPage.setList(financeManagementDao.findDeatil(finance));
		return userPage;
	}

	@Transactional
	public void saveMoney(FinanceDetail financeDetail) {
		//BigDecimal nowMoney = financeManagementDao.findMoney(financeDetail);
		financeDetail.preInsert();
		SimpleDateFormat data = new SimpleDateFormat("yyyyMMddHHmmss");
		StringBuilder sb = new StringBuilder();
		sb.append("1".equals(financeDetail.getType())?"CK":"QK").append(data.format(new Date()));
		financeDetail.setSerialNo(sb.toString());
		financeDetail.setJsAccountId(UserUtils.getUser().getId());
		/*if("1".equals(financeDetail.getType())){
			financeDetail.setMoneyStr(nowMoney.add(financeDetail.getMoney()).toString());
		}else {
			financeDetail.setMoneyStr(nowMoney.subtract(financeDetail.getMoney()).toString());
		}*/
		financeManagementDao.saveGlide(financeDetail);
		financeManagementDao.saveMoney(financeDetail);
		financeDetail.setUserId(UserUtils.getUser().getId());
		financeManagementDao.saveBankMoney(financeDetail);
	}

    @Transactional
    public void saveDkMoney(FinanceDetail financeDetail) {
        financeDetail.preInsert();
        SimpleDateFormat data = new SimpleDateFormat("yyyyMMddHHmmss");
        StringBuilder sb = new StringBuilder();
        sb.append("3".equals(financeDetail.getType())?"DK":"HK").append(data.format(new Date()));
        financeDetail.setSerialNo(sb.toString());
        financeDetail.setJsAccountId(UserUtils.getUser().getId());
		/*if("1".equals(financeDetail.getType())){
			financeDetail.setMoneyStr(nowMoney.add(financeDetail.getMoney()).toString());
		}else {
			financeDetail.setMoneyStr(nowMoney.subtract(financeDetail.getMoney()).toString());
		}*/
        financeManagementDao.saveGlide(financeDetail);
        financeManagementDao.saveDkMoney(financeDetail);
        financeManagementDao.restDkMoney(financeDetail);
    }

    public Finance findDkHkMoney(Finance finance) {
        return financeManagementDao.findDkHkMoney(finance);
    }

    public Page<FinanceDetail> monthDetail(Page<FinanceDetail> financeDetailPage, Finance finance) {
        FinanceDetail financeDetail = new FinanceDetail();
        financeDetail.setPage(financeDetailPage);
        financeDetailPage.setList(financeManagementDao.findMonthDetailfinance(finance));
        return financeDetailPage;
    }

    public Page<FinanceDetail> totalDetail(Page<FinanceDetail> financeDetailPage, Finance finance) {
        FinanceDetail financeDetail = new FinanceDetail();
        financeDetail.setPage(financeDetailPage);
        financeDetailPage.setList(financeManagementDao.findMonthDetailfinance(finance));
        return financeDetailPage;
    }

    public Rate getNowRate() {
        return financeManagementDao.getNowRate();
    }

    @Transactional
    public void saveRate(Rate rate) {
        if(StringUtils.isEmpty(rate.getId())){
            rate.preInsert();
            financeManagementDao.insertRate(rate);
        }else {
            financeManagementDao.updateRate(rate);
        }
    }

    public Page<FinanceDetail> findDeatilList(Page<FinanceDetail> financePage, Finance finance) {
        FinanceDetail financeDetail = new FinanceDetail();
        financeDetail.setPage(financePage);
        financePage.setList(financeManagementDao.findDeatilList(finance));
       return financePage;
    }

    @Transactional
    public void updatePassword(User user) {
        financeManagementDao.updatePassword(user);
    }

    @Transactional
    public void saveToperson(FinanceDetail finance) {
        String jsAccountId = financeManagementDao.findJsAccount(finance);
        finance.preInsert();
        SimpleDateFormat data = new SimpleDateFormat("yyyyMMddHHmmss");
        String zz = "ZZ" + data.format(new Date());
        finance.setSerialNo(zz);
        finance.setJsAccountId(jsAccountId);
        //转账明细
        financeManagementDao.insertPerson(finance);
        financeManagementDao.saveToperson1(finance);
        financeManagementDao.saveToperson2(finance);
    }

    public boolean findBankId(String bankId) {
        bankId =  bankId.substring(1);
       User user =  financeManagementDao.findBankId(bankId);
        if(null != user){
            return true;
        }
        return false;
    }

    public boolean findName(String bankId,String name) {
        User user =  financeManagementDao.findBankId(bankId);
        if(null != user){
            if(name.equals(user.getName())){
                return true;
            }
        }
        return false;
    }


/*
	public Page<User> findDateList(Page<User> userPage, User user) {
		return super.findPage(userPage, user);
	}

	@Transactional
	public void save(User user){
		super.save(user);
	}

	@Transactional
	public void updatePassword(User user) {
		depositorDao.updatePassword(user);
	}*/
}
