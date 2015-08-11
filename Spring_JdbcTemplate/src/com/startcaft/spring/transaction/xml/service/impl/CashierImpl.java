package com.startcaft.spring.transaction.xml.service.impl;

import java.util.List;

import com.startcaft.spring.transaction.xml.service.BookShopService;
import com.startcaft.spring.transaction.xml.service.Cashier;


public class CashierImpl implements Cashier {
	
	private BookShopService bookShopService;
	
	public void setBookShopService(BookShopService bookShopService) {
		this.bookShopService = bookShopService;
	}
	
	public void checkout(String username, List<String> isbns) {
		
		try {
			Thread.sleep(1000);//超时3秒，睡5秒，事务直接回滚
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (String isbn : isbns) {
			bookShopService.bayBook(username, isbn);
		}
	}
}
