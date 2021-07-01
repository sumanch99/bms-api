package com.cts.bms.bmsapi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bms.bmsapi.dao.AccountDao;
import com.cts.bms.bmsapi.exception.BmsException;
import com.cts.bms.bmsapi.model.Account;
import com.cts.bms.bmsapi.model.DebitCard;



@Service
public class ATMService {
	private static final Logger logger=LogManager.getLogger(ATMService.class);
	@Autowired
	CardService service;

	@Autowired
	AccountDao dao;

	public double getDebitCardBalance(DebitCard card) throws BmsException {
		logger.info("START");
		card = service.getDebitCard(card.getCardNo(), card.getCvvNo(), card.getPin());
		if(card==null) {
			logger.error("ATM card is not valid");
			throw new BmsException("ATM card is not valid");
		}
		if(service.getDebitCard(card.getCardNo(), card.getCvvNo(), card.getPin())!=null) {
			Account account = dao.getAccountWithAccountNumber(card.getAccountNo());
			if(account!=null) {
				logger.info("END");
				return account.getBalance();
			}
		}
		logger.error("ATM card is not valid");
		throw new BmsException("ATM card is not valid");
	}
	
	public boolean validateDebitCardWithDraw(DebitCard card,double amount) {
		logger.info("START");
		card = service.getDebitCard(card.getCardNo(), card.getCvvNo(), card.getPin());
		if(card==null) {
			logger.warn("BAD REQUEST");
			return false;
		}
		if(service.getDebitCard(card.getCardNo(), card.getCvvNo(), card.getPin())!=null) {
			Account account = dao.getAccountWithAccountNumber(card.getAccountNo());
			if(account!=null) {
				if(account.getBalance()>amount) {
					logger.info("END");
					return true;
				}
			}
		}
		logger.warn("BAD REQUEST");
		return false;
	}

	public boolean withdrawWithDebitCard(DebitCard card,double amount) {
		logger.info("START");
		card = service.getDebitCard(card.getCardNo(), card.getCvvNo(), card.getPin());
		Account account = dao.getAccountWithAccountNumber(card.getAccountNo());
		if(account!=null) {
			try {
				logger.info("END");
				return dao.withDrawThroughDebitCard(account, amount);
			} catch (BmsException e) {
				logger.error(e.getMessage());
				return false;
			}
		}
		logger.warn("BAD REQUEST");
		return false;
	}
	
}
