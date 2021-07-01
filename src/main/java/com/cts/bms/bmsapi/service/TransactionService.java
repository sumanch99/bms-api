package com.cts.bms.bmsapi.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bms.bmsapi.dao.AccountDao;
import com.cts.bms.bmsapi.dao.TransactionDao;
import com.cts.bms.bmsapi.model.Account;
import com.cts.bms.bmsapi.model.Transaction;




@Service
public class TransactionService {
	private static final Logger logger=LogManager.getLogger( TransactionService.class);
	@Autowired
	TransactionDao dao;
	
	@Autowired
	AccountDao accountDao;
	
	public List<Transaction> viewAllTransactions(){
		logger.info("VIEWALLTRANSACTIONS");
		return dao.viewAllTransaction();
		
	}
	
	public List<Transaction> viewAllTransactions(long accountNo){
		logger.info("START");
		Account account = accountDao.getAccountWithAccountNumber(accountNo);
		if(account!=null) {
			logger.info("END");
			return dao.viewAllTransaction(account);
		}
		logger.warn("BAD RETURN");
		return null;
		
	}
}
