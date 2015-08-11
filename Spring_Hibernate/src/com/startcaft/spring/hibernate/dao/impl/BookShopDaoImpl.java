package com.startcaft.spring.hibernate.dao.impl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.startcaft.spring.hibernate.dao.BookShopDAO;
import com.startcaft.spring.hibernate.exception.BookStockException;
import com.startcaft.spring.hibernate.exception.UserAccountException;

@Repository
public class BookShopDaoImpl implements BookShopDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//不推荐使用HibernateTemplate 和 HibernateDaoSupport
	//因为这样会导致Dao 和 Spring 的API 进行耦合
	//可移植性差
	//private HibernateTemplate hibernateTemplate;
	
	/**
	 * 获取和当前线程绑定的Session对象
	 * @return
	 */
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public int findBookPriceByIsbn(String isbn) {
		String hql = "select b.price from Book b where b.isbn = ?";
		Query query = this.getSession().createQuery(hql).setString(0, isbn);
		return (int) query.uniqueResult();
	}

	@Override
	public boolean updateBookStock(String isbn) {
		//先验证书的库存是否足够
		String hql = "select b.stock from Book b where b.isbn = ?";
		int stock = (int) getSession().createQuery(hql).setString(0, isbn).uniqueResult();
		if(stock == 0){
			throw new BookStockException("库存不足!");
		}
		
		hql = "update Book b set b.stock = b.stock - 1 where b.isbn = ?";
		return getSession().createQuery(hql).setString(0, isbn).executeUpdate() > 0;
	}

	@Override
	public boolean updateUserBalace(String username, int price) {
		//验证用户余额是否足够
		String hql = "select a.balance from Account a where a.userName = ?";
		int balance = (int) getSession().createQuery(hql).setString(0, username).uniqueResult();
		if(balance < price){
			throw new UserAccountException("用户余额不足!");
		}
		
		hql = "update Account a set a.balance = a.balance - ? where a.userName = ?";
		return getSession().createQuery(hql).setInteger(0, price).setString(1, username).executeUpdate() > 0;
	}

}
