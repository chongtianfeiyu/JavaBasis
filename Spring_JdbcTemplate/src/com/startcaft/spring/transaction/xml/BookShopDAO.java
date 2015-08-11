package com.startcaft.spring.transaction.xml;

public interface BookShopDAO {

	int findBookPriceByIsbn(String isbn);
	
	boolean updateBookStock(String isbn);
	
	boolean updateUserBalace(String username,int price);
}
