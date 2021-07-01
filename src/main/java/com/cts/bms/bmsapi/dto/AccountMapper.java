package com.cts.bms.bmsapi.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cts.bms.bmsapi.model.Account;



public class AccountMapper implements RowMapper<Account> {

	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		Account account = new Account();
		account.setAccNo(rs.getLong("account_no"));
		account.setAccType(rs.getString("account_type"));
		account.setAdhaarNumber(rs.getLong("adhaar_no"));
		account.setBalance(rs.getDouble("balance"));
		account.setIfscCode(rs.getString("ifsc_code"));
		account.setActive(rs.getBoolean("active"));
		account.setPhoneNumber(rs.getString("phonenumber"));
		account.setIntroducerAccountNo(rs.getLong("introducerAccountNo"));
		account.setNomineeAdhaarNo(rs.getString("nomineeAdhaarNo"));
		return account;
	}
	

}
