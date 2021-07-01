package com.cts.bms.bmsapi.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cts.bms.bmsapi.model.InterestRate;


public class InterestRateMapper implements RowMapper<InterestRate>  {

	@Override
	public InterestRate mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		InterestRate irate = new InterestRate();
		irate.setPlan(rs.getString("plan"));
		irate.setRate(rs.getDouble("rate"));
		return irate;
	}
	
	

}
