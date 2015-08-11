package com.startcaft.hibernate.dao.impl;

import org.springframework.stereotype.Repository;

import com.startcaft.hibernate.dao.CustomerDao;
import com.startcaft.hibernate.entity.Customer;

/**
 * CustomerDao 的实现类，继承自BaseDaoImpl接口
 */
@Repository
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao{
	
}