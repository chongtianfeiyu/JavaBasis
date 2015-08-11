package com.startcaft.spring.hibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startcaft.spring.hibernate.dao.BookShopDAO;
import com.startcaft.spring.hibernate.service.BookShopService;

@Service
public class BookShopServiceImpl implements BookShopService {

	@Autowired
	private BookShopDAO bookShopDao;
	
	@Override
	public void bayBook(String username, String isbn) {
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		bookShopDao.updateBookStock(isbn);
		bookShopDao.updateUserBalace(username, price);
	}
}
