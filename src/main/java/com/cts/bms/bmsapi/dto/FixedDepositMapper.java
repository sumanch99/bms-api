package com.cts.bms.bmsapi.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cts.bms.bmsapi.model.FixedDeposit;

public class FixedDepositMapper implements RowMapper<FixedDeposit> {

	@Override
	public FixedDeposit mapRow(ResultSet rs, int rowNum) throws SQLException {
		FixedDeposit fd = new FixedDeposit();
		fd.setAccount_number(rs.getLong("account_no"));
		fd.setAmount(rs.getDouble("amount"));
		fd.setFixed_deposit_id(rs.getInt("fd_id"));
		fd.setInterest(rs.getDouble("interest"));
		fd.setMatured_amount(rs.getDouble("matured_amount"));
		fd.setStart_date(rs.getDate("start_date"));
		fd.setTenure(rs.getInt("tenure"));
		return fd;
	}

}
