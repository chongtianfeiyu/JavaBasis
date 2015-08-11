package com.startcaft.hibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startcaft.hibernate.dao.BaseDao;
import com.startcaft.hibernate.entity.Customer;
import com.startcaft.hibernate.service.CustomerService;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements CustomerService<Customer> {
	
	/**
	 * ×¢ÈëDAO
	 */
	@Autowired
	@Override
	public void setDao(BaseDao<Customer> dao) {
		super.setDao(dao);
	}
}
