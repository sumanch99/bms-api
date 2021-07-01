package com.cts.bms.bmsapi.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cts.bms.bmsapi.model.RecurringDeposit;

public class RecurringDepositMapper implements RowMapper<RecurringDeposit> {

	@Override
	public RecurringDeposit mapRow(ResultSet rs, int rowNum) throws SQLException {
		RecurringDeposit rd = new RecurringDeposit();
		rd.setAccountNumber(rs.getLong("account_no"));
		rd.setInterest(rs.getDouble("interest"));
		rd.setMaturedAmount(rs.getDouble("matured_amount"));
		rd.setMonthlyAmount(rs.getDouble("monthly_amount"));
		rd.setRecurringDepositId(rs.getInt("rd_id"));
		rd.setStartDate(rs.getDate("start_date"));
		rd.setTenure(rs.getInt("tenure"));
		return rd;
	}

}
