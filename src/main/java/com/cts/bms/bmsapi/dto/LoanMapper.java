package com.cts.bms.bmsapi.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cts.bms.bmsapi.model.Loan;


public class LoanMapper implements RowMapper<Loan> {

	@Override
	public Loan mapRow(ResultSet rs, int rowNum) throws SQLException {
		Loan loan = new Loan();
		loan.setLoan_id(rs.getLong("loan_id"));
		loan.setLoanCode(rs.getString("loan_code"));
		loan.setAccNo(rs.getLong("account_no"));
		loan.setAmount(rs.getDouble("amount"));
		loan.setGurantorAccNo(rs.getLong("gurantor"));
		loan.setTenure(rs.getInt("tenure"));
		loan.setStartDate(rs.getDate("start_date"));
		loan.setApproved(rs.getBoolean("approved"));
		return loan;
	}

}
