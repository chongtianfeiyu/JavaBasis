package com.startcaft.spring.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {
	
	@Autowired
	private BookShopDAO bookShopDao;

	//使用 @Transactional 注解来标注一个事务
	@Transactional
	@Override
	public void bayBook(String username, String isbn) {
		//1,获取数的价格
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		//2,更新书的库存
		bookShopDao.updateBookStock(isbn);
		//3,更新账户
		bookShopDao.updateUserBalace(username, price);
	}
}
