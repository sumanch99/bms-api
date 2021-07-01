package com.cts.bms.bmsapi.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bms.bmsapi.dao.AccountDao;
import com.cts.bms.bmsapi.dao.AdminDao;
import com.cts.bms.bmsapi.dao.BranchDao;
import com.cts.bms.bmsapi.dao.CustomerDao;
import com.cts.bms.bmsapi.exception.BmsException;
import com.cts.bms.bmsapi.model.Account;
import com.cts.bms.bmsapi.model.Branch;
import com.cts.bms.bmsapi.model.Loan;



@Service
public class LoanService {
	private static final Logger logger=LogManager.getLogger(LoanService.class);

	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	AdminDao adminDao;
	
	@Autowired
	BranchDao branchDao;
	
	@Autowired
	AccountDao accountDao;
	
	public boolean applyForLoan(Loan loan) {
		logger.info("START");
		try {
			if(customerDao.getActiveAccount(loan.getAccNo())!=null && customerDao.getActiveAccount(loan.getGurantorAccNo())!=null) {
				customerDao.applyForLoan(loan);
				logger.info("END");
				return true;
			}
			logger.warn("BAD REQUEST");
			return false;
		}catch(BmsException e) {
			logger.error(e.getMessage());
			return false;
		}
		
	}
	
	public boolean grantLoan(long loanId) {
		logger.info("START");
		try {
			Loan loan = adminDao.getLoan(loanId);
			if(loan==null) {
				logger.warn("BAD REQUEST");
				return false;
			}
			Account account = accountDao.getAccountWithAccountNumber(loan.getAccNo());
			if(account==null) {
				logger.warn("BAD REQUEST");
				return false;
			}
			else {
				Branch branch = branchDao.getBranch(account.getIfscCode());
				if(branch == null) {
					logger.warn("BAD REQUEST");
					return false;
				}
				if(branch.getBranchFund()<loan.getAmount()) {
					logger.warn("BAD REQUEST");
					return false;
				}
				if(branchDao.deductFromBranch(account.getIfscCode(),loan.getAmount())) {
					if(adminDao.approveLoanRequest(loan.getLoan_id(), true)) {
						accountDao.depositIntoAccount(account, loan.getAmount());
						logger.info("END");
						return true;
					}
				}
			}
			
		} catch (BmsException e) {
			logger.error(e.getMessage());
			return false;
		}
		logger.warn("BAD REQUEST");
		return false;
	}

	public List<Loan> getAllApprovedLoans() {
		logger.warn("GET ALL APPROVED LOANS");
		return adminDao.viewAllApprovedLoans();
	}
	
	
	
	
}
