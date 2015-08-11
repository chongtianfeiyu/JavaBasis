package com.startcaft.spring.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.startcaft.spring.transaction.BookShopDAO;

@Repository("bookShopDao")
public class BookShopDaoImpl implements BookShopDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int findBookPriceByIsbn(String isbn) {
		String sql = "select price from book where isbn = ?";
		int price = jdbcTemplate.queryForObject(sql, Integer.class, isbn);
		return price;
	}

	@Override
	public boolean updateBookStock(String isbn) {
		String sql = "select stock from book_stock where isbn = ?";
		int result = jdbcTemplate.queryForObject(sql, Integer.class, isbn);
		
		if(result == 0){
			throw new BookStockException("库存不足!");
		}
		
		sql = "update book_stock set stock = stock - 1 where isbn = ?";
		result = jdbcTemplate.update(sql, isbn);
		return result > 0;
	}

	@Override
	public boolean updateUserBalace(String username, int price) {
		String sql = "select balace from account where username = ?";
		int result = jdbcTemplate.queryForObject(sql, Integer.class, username);
		
		if(result - price < 0){
			throw new BookStockException("余额不足!");
		}
		
		sql = "update account set balace = balace - ? where username = ?";
		result = jdbcTemplate.update(sql, price,username);
		return result > 0;
	}

}
