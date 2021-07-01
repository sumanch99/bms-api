package com.cts.bms.bmsapi.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cts.bms.bmsapi.dto.TransactionMapper;
import com.cts.bms.bmsapi.model.Account;
import com.cts.bms.bmsapi.model.Transaction;



@Repository
public class TransactionDaoImpl implements TransactionDao {
	private static final Logger logger=LogManager.getLogger(TransactionDaoImpl.class);
	@Autowired
	JdbcTemplate jdbcTemplate;

	@SuppressWarnings("deprecation")
	@Override
	public List<Transaction> viewAllTransaction(Account account) {
		logger.info("START");
		try {
			String query = "select * from transaction where from_account = ? or to_account = ? order by date desc";
			List<Transaction> transactions = jdbcTemplate.query(query,new Object[] {
					account.getAccNo(),
					account.getAccNo()
			}, new TransactionMapper());
			logger.info("END");
			return transactions;
		}catch(DataAccessException e) {
			logger.error(e.getMessage());
			return null;
		}
		
	}

	@Override
	public List<Transaction> viewAllTransaction() {
		logger.info("START");
		try {
			String query = "select * from transaction order by date desc";
			List<Transaction> transactions = jdbcTemplate.query(query, new TransactionMapper());
			logger.info("END");
			return transactions;
		}catch(DataAccessException e) {
			logger.error(e.getMessage());
			return null;
		}
	}


}
