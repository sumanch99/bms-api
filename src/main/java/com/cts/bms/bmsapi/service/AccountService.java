package com.cts.bms.bmsapi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bms.bmsapi.dao.AccountDao;
import com.cts.bms.bmsapi.exception.BmsException;
import com.cts.bms.bmsapi.model.Account;




@Service
public class AccountService {
	private static final Logger logger=LogManager.getLogger(AccountService.class);
	@Autowired
	AccountDao dao;

	public boolean depositIntoAccount(Account account, double amount) {
		logger.info("START");
		try {
			logger.info("END");
			return dao.depositIntoAccount(account, amount);
		} catch (BmsException e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	public boolean withdrawFromAccount(Account account, double amount) {
		logger.info("START");
		try {
			if(account.getBalance()<amount) {
				logger.warn("BAD RETURN");
				return false;
			}
			logger.info("END");
			return dao.withdrawFromAccount(account, amount);
		} catch (BmsException e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	
	public boolean accountToAccountTransfer(long payeeAccountNo,long receivingAccountNo,double amount) throws BmsException {
		logger.info("START");
		Account receivingAccount = dao.getAccountWithAccountNumber(receivingAccountNo);
		Account payeeAccount = dao.getAccountWithAccountNumber(payeeAccountNo);
		if(receivingAccount!=null && payeeAccount!=null) {
			if(payeeAccount.getBalance()<amount) {
				logger.error("Insufficient balance");
				throw new BmsException("Insufficient balance");
			}
			logger.info("END");
			return dao.accountToAccountTransfer(payeeAccount, receivingAccount, amount);
		}
		logger.warn("BAD RETURN");
		return false;
			
	}
	
	public Account checkBalance(long accountNo) {
		logger.info("checkbalance");
		return dao.getAccountWithAccountNumber(accountNo);
	}
	

}
