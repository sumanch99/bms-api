package com.cts.bms.bmsapi.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bms.bmsapi.dao.BranchDao;
import com.cts.bms.bmsapi.exception.BmsException;
import com.cts.bms.bmsapi.model.Branch;




@Service
public class BranchService {
	private static final Logger logger=LogManager.getLogger(BranchService.class);
	
	@Autowired
	BranchDao branchDao;
	public List<Branch> getAllBranches(){
		logger.info("GET ALL BRANCHES");
		return branchDao.getAllBranches();
	}
	
	public boolean depositIntoBranch(String ifscCode,double amount){
		logger.info("START");
		try {
			logger.info("END");
			return branchDao.depositIntoBranch(ifscCode, amount);
		} catch (BmsException e) {
			logger.error(e.getMessage());
			return false;
		}
		
	}
	
	public boolean deductFromBranch(String ifscCode,double amount){
		logger.info("START");
		try {
			logger.info("END");
			return branchDao.deductFromBranch(ifscCode, amount);
		} catch (BmsException e) {
			logger.error(e.getMessage());
			return false;
		}
	}
}
