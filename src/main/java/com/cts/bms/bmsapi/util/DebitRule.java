package com.cts.bms.bmsapi.util;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cts.bms.bmsapi.model.Account;
import com.cts.bms.bmsapi.model.DebitCard;

public class DebitRule {
	private static final Logger logger=LogManager.getLogger(DebitRule.class);
	private static final double MIN_BALANCE = 999 ;
	
	public static boolean isDebitCardPossible(DebitCard card, Account account) {
		logger.info("START");	
		if(card.getAccountNo()==account.getAccNo()) {
			if(account.getBalance()>MIN_BALANCE) {
				logger.info("END");	
				return true;
			}
		}
		logger.error("debit card not possible");
		return false;
	} 
	
	public static int generateCvv() {
		logger.info("START");
		Random random = new Random();
		logger.info("END");
		return random.nextInt(900) + 100;
	}
	
}
