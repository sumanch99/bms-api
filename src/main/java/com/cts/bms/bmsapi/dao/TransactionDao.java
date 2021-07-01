package com.cts.bms.bmsapi.dao;

import java.util.List;

import com.cts.bms.bmsapi.model.Account;
import com.cts.bms.bmsapi.model.Transaction;



public interface TransactionDao {
	
	public List<Transaction> viewAllTransaction(Account account);
	public List<Transaction> viewAllTransaction();
	
}
