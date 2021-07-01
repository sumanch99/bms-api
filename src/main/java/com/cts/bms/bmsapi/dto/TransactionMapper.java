package com.cts.bms.bmsapi.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cts.bms.bmsapi.model.Transaction;


public class TransactionMapper implements RowMapper<Transaction> {

	@Override
	public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
		Transaction transaction = new Transaction();
		transaction.setFromAccount(rs.getLong("from_account"));
		transaction.setToAccount(rs.getLong("to_account"));
		transaction.setAmount(rs.getDouble("amount"));
		transaction.setDate(rs.getTimestamp("date").toString());
		transaction.setFlag(rs.getBoolean("flag"));
		transaction.setAtmFlag(rs.getBoolean("atm_flag"));
		return transaction;
	}

}
