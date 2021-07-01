package com.cts.bms.bmsapi.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bms.bmsapi.dao.AccountDao;
import com.cts.bms.bmsapi.dao.CustomerDao;
import com.cts.bms.bmsapi.exception.AdhaarNumberNotFoundException;
import com.cts.bms.bmsapi.exception.BmsException;
import com.cts.bms.bmsapi.model.Account;
import com.cts.bms.bmsapi.model.Customer;



@Service
public class CustomerService {
	private static final Logger logger=LogManager.getLogger( CustomerService.class);
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	AccountDao accountDao;

	public Customer createNewUser(Customer customer) throws AdhaarNumberNotFoundException{
		logger.info("START");
		System.out.println(customer);
		try {
			if(accountDao.checkAdhaarNo(customer).isEmpty()) {
				logger.error("No accounts found");
				throw new AdhaarNumberNotFoundException("No accounts found");
			}
			customer.setApproved(true);
			logger.info("END");
			return customerDao.addCustomer(customer);
		} catch (BmsException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public List<Account> getAllAccounts(String userId) {
		logger.info("START");
		try {
			List<Account> accounts = customerDao.getAllAccountsOfCustomer(userId);
			logger.info("END");
			return accounts;
		}catch(BmsException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
}
