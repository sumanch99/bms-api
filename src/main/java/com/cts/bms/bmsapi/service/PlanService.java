package com.cts.bms.bmsapi.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bms.bmsapi.dao.AccountDao;
import com.cts.bms.bmsapi.dao.AdminDao;
import com.cts.bms.bmsapi.dao.CustomerDao;
import com.cts.bms.bmsapi.exception.BmsException;
import com.cts.bms.bmsapi.model.Account;
import com.cts.bms.bmsapi.model.FixedDeposit;
import com.cts.bms.bmsapi.model.InterestRate;
import com.cts.bms.bmsapi.model.RecurringDeposit;
import com.cts.bms.bmsapi.util.FixedDepositUtil;
import com.cts.bms.bmsapi.util.RecurringDepositUtil;




@Service
public class PlanService {
	private static final Logger LOGGER=LogManager.getLogger(PlanService.class);
	@Autowired
	AdminDao adminDao;
	
	@Autowired
	AccountDao accountDao;
	
	@Autowired
	CustomerDao custDao;
	
	public List<InterestRate> viewAllPlans() {
		LOGGER.info("View Deatils");
		return adminDao.viewAllPlans();
	}
	
	public boolean generateFixedDeposit(FixedDeposit fd) {
		LOGGER.info("START");
		fd.setInterest(custDao.getFdInterestRate());
		double maturedAmount = FixedDepositUtil.getMaturedAmount(fd.getAmount(), fd.getInterest(), fd.getTenure());
		fd.setMatured_amount(maturedAmount);
		try {
			custDao.applyForFd(fd);
			LOGGER.info("END");
			return true;
		} catch (BmsException e) {
			LOGGER.error(e.getMessage());
			return false;
		}
	}
	
	public boolean generateRecurringDeposit(RecurringDeposit rd) {
		LOGGER.info("START");
		rd.setInterest(custDao.getRdInterestRate());
		double maturedAmount = RecurringDepositUtil.getMaturedAmount(rd.getMonthlyAmount(), rd.getInterest(), rd.getTenure());
		rd.setMaturedAmount(maturedAmount);
		try {
			custDao.applyForRd(rd);
			LOGGER.info("END");
			return true;
		} catch (BmsException e) {
			LOGGER.error(e.getMessage());
			return false;
		}
	}
	
	public List<FixedDeposit> getAllFixedDeposits(long accountNo) {
		LOGGER.info("START");
		Account account = accountDao.getAccountWithAccountNumber(accountNo);
		if(account!=null) {
			try {
				LOGGER.info("END");
				return custDao.getAllFixedDeposits(account);
			} catch (BmsException e) {
				LOGGER.error(e.getMessage());
				return null;
			}
		}
		LOGGER.info("BAD-REQUEST");
		return null;
	}
	
	public List<RecurringDeposit> getAllRecurringDeposits(long accountNo) {
		LOGGER.info("START");
		Account account = accountDao.getAccountWithAccountNumber(accountNo);
		if(account!=null) {
			try {
				LOGGER.info("END");
				return custDao.getAllRecurringDeposits(account);
			} catch (BmsException e) {
				LOGGER.error(e.getMessage());
				return null;
			}
		}
		LOGGER.info("BAD-REQUEST");
		return null;
	}
}
