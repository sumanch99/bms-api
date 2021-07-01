package com.cts.bms.bmsapi.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.bms.bmsapi.dao.AdminDao;
import com.cts.bms.bmsapi.dao.CustomerDao;
import com.cts.bms.bmsapi.exception.BmsException;
import com.cts.bms.bmsapi.model.Admin;
import com.cts.bms.bmsapi.model.Customer;

@Service
public class BmsUserDetailsService implements UserDetailsService {
	private static final Logger logger=LogManager.getLogger(BmsUserDetailsService.class);
	@Autowired
	AdminDao adminDao;
	
	@Autowired
	CustomerDao customerDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("START");
		if ( username == null || username.isEmpty() ){
			System.out.println("here");
			logger.warn("BAD REQUEST");
	        throw new UsernameNotFoundException("username is empty");
	    }
		
		try {
			Admin admin = adminDao.loadAdmin(username);
			if(admin!=null) {
				logger.info("END");
				return new SecurityUser(Long.toString(admin.getEmpId()), admin.getPassword(),"ADMIN");
			}
			Customer customer = customerDao.loadCustomer(username);
			if(customer!=null) {
				logger.info("END");
				return new SecurityUser(customer.getUserId(), customer.getPassword(),"CUSTOMER");
			}
		} catch (BmsException e) {
			logger.info(e.getMessage());
			throw new UsernameNotFoundException("Credential not found");
		}
		logger.error("Credential not found");
		throw new UsernameNotFoundException("Credential not found");
	}

}
