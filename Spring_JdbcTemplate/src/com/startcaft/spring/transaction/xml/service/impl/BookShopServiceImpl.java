package com.startcaft.spring.transaction.xml.service.impl;

import com.startcaft.spring.transaction.xml.BookShopDAO;
import com.startcaft.spring.transaction.xml.service.BookShopService;


public class BookShopServiceImpl implements BookShopService {
	
	private BookShopDAO bookShopDao;
	
	public void setBookShopDao(BookShopDAO bookShopDao) {
		this.bookShopDao = bookShopDao;
	}

	public void bayBook(String username, String isbn) {
		//1,获取数的价格
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		//2,更新书的库存
		bookShopDao.updateBookStock(isbn);
		//3,更新账户
		bookShopDao.updateUserBalace(username, price);
	}
}
