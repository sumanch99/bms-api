package com.cts.bms.bmsapi.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cts.bms.bmsapi.model.Branch;


public class BranchMapper implements RowMapper<Branch> {

	@Override
	public Branch mapRow(ResultSet rs, int rowNum) throws SQLException {
		Branch branch = new Branch();
		branch.setIfscCode(rs.getString("ifsc_code"));
		branch.setPincode(rs.getInt("pincode"));
		branch.setAddress(rs.getString("address"));
		branch.setBranchFund(rs.getDouble("branch_fund"));
		branch.setBranchName(rs.getString("branch_name"));
		return branch;
	}
	
}