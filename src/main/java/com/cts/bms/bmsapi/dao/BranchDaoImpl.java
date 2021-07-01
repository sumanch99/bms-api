package com.cts.bms.bmsapi.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cts.bms.bmsapi.dto.BranchMapper;
import com.cts.bms.bmsapi.exception.BmsException;
import com.cts.bms.bmsapi.model.Branch;

@Repository
public class BranchDaoImpl implements BranchDao {
	private static final Logger logger = LogManager.getLogger(BranchDaoImpl.class);
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public boolean depositIntoBranch(String ifscCode, double amount) throws BmsException {
		logger.info("START");
		String query = "update branch set branch_fund = branch_fund+? where ifsc_code = ? ";
		try {
			jdbcTemplate.update(query, new Object[] { amount, ifscCode });
			logger.info("END");
			return true;
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
			throw new BmsException("Deposit to bank branch not possible");
		}
	}

	@Override
	public boolean deductFromBranch(String ifscCode, double amount) throws BmsException {
		logger.info("START");
		String query = "update branch set branch_fund = branch_fund-? where ifsc_code = ? ";
		try {
			jdbcTemplate.update(query, new Object[] { amount, ifscCode });
			logger.info("END");
			return true;
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
			throw new BmsException("Duduction from bank branch not possible");
		}
	}

	@Override
	public List<Branch> getAllBranches() {
		logger.info("START");
		try {
			String query = "select * from branch order by branch_name";
			List<Branch> branches = jdbcTemplate.query(query, new BranchMapper());
			logger.info("END");
			return branches;
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Branch getBranch(String ifscCode) {
		logger.info("START");
		String query = "select * from branch where ifsc_code = '" + ifscCode + "'";
		try {
			Branch branch = jdbcTemplate.queryForObject(query, new BranchMapper());
			logger.info("END");
			return branch;
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

}
