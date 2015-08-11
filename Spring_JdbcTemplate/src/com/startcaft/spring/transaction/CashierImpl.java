package com.startcaft.spring.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CashierImpl implements Cashier {
	
	@Autowired
	private BookShopService bookShopService;
	
	@Transactional(
			propagation=Propagation.REQUIRES_NEW,
			isolation=Isolation.READ_COMMITTED,
			noRollbackFor={UserAccountException.class},
			timeout=3)
	@Override
	public void checkout(String username, List<String> isbns) {
		
		try {
			Thread.sleep(5000);//超时3秒，睡5秒，事务直接回滚
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (String isbn : isbns) {
			bookShopService.bayBook(username, isbn);
		}
	}
}
