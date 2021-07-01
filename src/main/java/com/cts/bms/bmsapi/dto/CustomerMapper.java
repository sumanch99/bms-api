package com.cts.bms.bmsapi.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cts.bms.bmsapi.model.Customer;


public class CustomerMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setUserId(rs.getString("userid"));
		customer.setPassword(rs.getString("password"));
		customer.setFirstName(rs.getString("fname"));
		customer.setLastName(rs.getString("lname"));
		customer.setGender(rs.getString("gender"));
		customer.setAddress(rs.getString("address"));
		customer.setTown(rs.getString("town"));
		customer.setDistrict(rs.getString("district"));
		customer.setState(rs.getString("state"));
		customer.setPinCode(rs.getInt("pin"));
		customer.setDateOfBirth(rs.getDate("dateofbirth"));
		customer.setEmailId(rs.getString("emailId"));
		customer.setPhoneNumber(rs.getString("phonenumber"));
		customer.setAdhaarNo(rs.getString("adhaarno"));
		customer.setApproved(rs.getBoolean("approved"));
		
		
		return customer;
		
	}

}
