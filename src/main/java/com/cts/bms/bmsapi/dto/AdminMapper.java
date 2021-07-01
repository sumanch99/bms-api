package com.cts.bms.bmsapi.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cts.bms.bmsapi.model.Admin;


public class AdminMapper implements RowMapper<Admin> {

	@Override
	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		Admin admin = new Admin();
		admin.setEmpId(rs.getLong("emp_id"));
		admin.setPassword(rs.getString("password"));
		admin.setAdhaarNo(rs.getLong("adhaar_no"));
		admin.setPhoneNo(rs.getLong("phone_number"));
		return admin;
	}

}
