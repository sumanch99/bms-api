package com.cts.bms.bmsapi.dao;

import java.util.List;

import com.cts.bms.bmsapi.exception.AdhaarNumberNotFoundException;
import com.cts.bms.bmsapi.exception.BmsException;
import com.cts.bms.bmsapi.model.Account;
import com.cts.bms.bmsapi.model.Customer;



public interface AccountDao {

	public List<Account> checkAdhaarNo(Customer customer)
			throws AdhaarNumberNotFoundException, AdhaarNumberNotFoundException;

	public boolean depositIntoAccount(Account account, double amount) throws BmsException;

	public boolean withdrawFromAccount(Account account, double amount) throws BmsException;

	public boolean accountToAccountTransfer(Account payeeAccount, Account receivingAccount, double amount);

	public Account getAccountWithAccountNumber(long accountNo);

	public boolean withDrawThroughDebitCard(Account account,double amount) throws BmsException;
	
	public List<Account> getAllAccounts();
}
