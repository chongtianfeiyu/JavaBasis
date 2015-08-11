package com.startcaft.spring.transaction;


import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class BookShopDaoTest {

	private ClassPathXmlApplicationContext context = null;
	private BookShopDAO bookShopDao = null;
	private BookShopService bookShopService = null;
	private Cashier cashier = null;

	{
		context = new ClassPathXmlApplicationContext("applicationcontext.xml");
		bookShopDao = context.getBean(BookShopDAO.class);
		bookShopService = context.getBean(BookShopService.class);
		cashier = context.getBean(Cashier.class);
	}
	
	@Test
	public void testSpringTransationAttribute(){
		cashier.checkout("AA", Arrays.asList("1001","1002"));
	}
	
	@Test
	public void bookShopServiceTest(){
		bookShopService.bayBook("AA", "1001");
	}
	
	@Test
	public void findBookPriceByIsbnTest() {
		System.out.println(bookShopDao.findBookPriceByIsbn("1001"));
	}
	
	@Test
	public void updateBookStockTest(){
		System.out.println(bookShopDao.updateBookStock("1001"));
	}
	
	@Test
	public void updateUserBalace(){
		System.out.println(bookShopDao.updateUserBalace("AA", 100));
	}
}
