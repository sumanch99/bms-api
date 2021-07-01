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
import com.cts.bms.bmsapi.exception.IntroducerNotFoundException;
import com.cts.bms.bmsapi.model.Account;
import com.cts.bms.bmsapi.model.Admin;
import com.cts.bms.bmsapi.model.Branch;
import com.cts.bms.bmsapi.model.Customer;
import com.cts.bms.bmsapi.model.Employee;
import com.cts.bms.bmsapi.model.InterestRate;
import com.cts.bms.bmsapi.model.Loan;




@Service
public class AdminService {
	private static final Logger logger=LogManager.getLogger(AdminService.class);
	@Autowired
	AdminDao dao;
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	AccountDao accountDao;
	
	public boolean createNewAdmin(Admin admin) {
		logger.info("START");
		try {
		Employee emp = dao.getEmployee(admin.getEmpId(),admin.getAdhaarNo(), admin.getPhoneNo());
		if(emp!=null) {
			dao.addAdmin(admin);
			logger.info("END");
			return true;
		}
		}catch(BmsException e) {
			logger.error(e.getMessage());
			return false;
		}
		logger.warn("BAD-REQUEST");
		return false;
	}
	
	public List<Customer> getAllCustomers() {
		logger.info("GET ALL CUSTOMERS");
		return dao.viewAllCustomer();
	}
	
	public List<Loan> getAllPendingLoanRequests(){
		logger.info("GET ALL PENDING LOAN REQUESTS");
		return dao.viewAllPendingLoanRequests();
	}
	
	public List<Account> getAllAccounts() {
		logger.info("GET ALL ACCOUNTS");
		return accountDao.getAllAccounts();
	}
	
	public List<Loan> getAllApprovedLoans(String userId){
		logger.info("GET ALL APPROVED LOANS");
		return customerDao.viewAllApprovedLoans(userId);
	}
	
	public List<Account> getAllAccountsFromCustomer(Customer customer){
		logger.info("GET ALL ACCOUNTS FROM CUSTOMER");
		return dao.viewAllAccountsOfCustomer(customer);
	}

	public boolean createNewAccount(Account account) throws IntroducerNotFoundException {
		logger.info("START");
		try {
				customerDao.getActiveAccount(account.getIntroducerAccountNo());
				account.setActive(true);
				logger.info("END");
				return dao.createAccount(account);
			}catch(BmsException e) {
				logger.error(e.getMessage());
				throw new IntroducerNotFoundException("No customer found with the given introducer account number");
			}
	}

	public boolean disableAccount(long accountNo) {
		logger.info("START");
		try {
			Account account = accountDao.getAccountWithAccountNumber(accountNo);
			if(account==null) {
				logger.warn("ACCOUNT NOT FOUND");
				return false;
			}
			dao.disableAccount(account.getAccNo());
			logger.info("END");
			return true;
		} catch (BmsException e) {
			logger.error(e.getMessage());
			return false;
		}

	}

	public boolean enableAccount(long accountNo) {
		logger.info("START");
		try {
			Account account = accountDao.getAccountWithAccountNumber(accountNo);
			if(account==null) {
				logger.warn("ACCOUNT NOT FOUND");
				return false;
			}
			dao.enableAccount(account.getAccNo());
			logger.info("END");
			return true;
		} catch (BmsException e) {
			logger.error(e.getMessage());
			return false;
		}

	}
	
	public boolean deleteLoanRequest(Long loanId) {
		logger.info("START");
		try {
			logger.info("END");
			return dao.deleteRejectedLoanRequest(loanId);
		} catch (BmsException e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	public boolean changeInterestRate(InterestRate irate) {
		logger.info("START");
		try {
			logger.info("END");
			return dao.changeInterestRate(irate);
		} catch (BmsException e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	
	public boolean addBankBranch(Branch branch) {
		logger.info("START");
		try {
			logger.info("END");
			return dao.addBankBranch(branch);
		} catch (BmsException e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	
}
