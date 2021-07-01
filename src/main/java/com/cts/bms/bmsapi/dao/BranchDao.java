package com.cts.bms.bmsapi.dao;

import java.util.List;

import com.cts.bms.bmsapi.exception.BmsException;
import com.cts.bms.bmsapi.model.Branch;


public interface BranchDao {
	public boolean depositIntoBranch(String ifscCode, double amount) throws BmsException;

	public boolean deductFromBranch(String ifscCode, double amount) throws BmsException;
	
	public List<Branch> getAllBranches();
	
	public Branch getBranch(String ifscCode);  
}
