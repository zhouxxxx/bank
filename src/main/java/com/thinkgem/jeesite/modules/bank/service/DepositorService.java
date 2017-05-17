/**
 * Copyright &copy; 2012-2013 <a href="httparamMap://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bank.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bank.dao.DepositorDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class DepositorService extends CrudService<DepositorDao, User> {

	@Autowired
	private DepositorDao depositorDao;

	public User get(String id) {
          return super.get(id);
	}


	public Page<User> findDateList(Page<User> userPage, User user) {
		return super.findPage(userPage, user);
	}

	@Transactional
	public void save(User user){
		super.save(user);
	}

	@Transactional
	public int updatePassword(User user) {
		return depositorDao.updatePassword(user);
	}

    public boolean checkName(String loginName) {
		String name  = depositorDao.checkName(loginName);
		return name != null? false:true;
    }
}
