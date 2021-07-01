package com.cts.bms.bmsapi.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bms.bmsapi.dao.AccountDao;
import com.cts.bms.bmsapi.dao.AdminDao;
import com.cts.bms.bmsapi.dao.CustomerDao;
import com.cts.bms.bmsapi.exception.AccountNotFoundException;
import com.cts.bms.bmsapi.exception.BmsException;
import com.cts.bms.bmsapi.model.Account;
import com.cts.bms.bmsapi.model.DebitCard;
import com.cts.bms.bmsapi.util.DebitRule;




@Service
public class CardService {
	private static final Logger logger=LogManager.getLogger(CardService.class);
	@Autowired 
	CustomerDao dao;
	@Autowired
	AccountDao accountDao;
	@Autowired
	AdminDao adminDao;
	
	public boolean applyForDebitCard(DebitCard card) throws AccountNotFoundException {
		logger.info("START");
		try {
			Account account = accountDao.getAccountWithAccountNumber(card.getAccountNo());
			if(account==null) {
				logger.error("account not found");
				throw new AccountNotFoundException("account not found");
			}
			if(DebitRule.isDebitCardPossible(card, account)) {
				card.setCvvNo(DebitRule.generateCvv());
				logger.info("END");
				return dao.applyForDebitCard(card);
			}
		}catch(BmsException e) {
			logger.error(e.getMessage());
			return false;
		}
		logger.warn("BAD REQUEST");
		return false;
	}
	
	public boolean approveDebitCard(long cardNo) {
		logger.info("START");
		try {
			DebitCard card = dao.getDebitCard(cardNo);
			if(card==null) {
				logger.info("CARD NOT FOUND");
				return false;
			}
			if(adminDao.approveDebitCard(card)) {
				logger.info("END");
				return true;
			}
		}catch(BmsException e) {
			logger.error(e.getMessage());
			return false;
		}
		logger.warn("BAD REQUEST");
		return false;
	}
	
	public boolean rejectDebitCard(long cardNo) {
		logger.info("START");
		try {
			if(adminDao.deleteRejectedDebitCard(cardNo)) {
				logger.info("END");
				return true;
			}
		}catch(BmsException e) {
			logger.error(e.getMessage());
			return false;
		}
		logger.warn("BAD REQUEST");
		return false;
	}
	
	public List<DebitCard> viewAllPendingDebitCards(){
		logger.info("START");
		try {
			List<DebitCard> cards = adminDao.viewAllPendingDebitCards();
			logger.info("END");
			return cards;
		}catch(BmsException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public List<DebitCard> getDebitCard(long accountNo) throws AccountNotFoundException{
		logger.info("START");
		try {
			Account account = accountDao.getAccountWithAccountNumber(accountNo);
			if(account==null) {
				logger.error("account not found");
				throw new AccountNotFoundException("account not found");
			}
			List<DebitCard> cards = dao.viewAllApprovedDebitCards(account);
			logger.info("END");
			return cards;
		}catch(BmsException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public DebitCard getDebitCard(long cardNo, int cvv,int pin) {
		logger.info("GET DEBIT CARD");
		return dao.getDebitCard(cardNo, cvv, pin);
	}
}
