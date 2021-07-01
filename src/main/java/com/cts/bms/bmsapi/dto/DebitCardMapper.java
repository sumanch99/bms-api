package com.cts.bms.bmsapi.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cts.bms.bmsapi.model.DebitCard;


public class DebitCardMapper implements RowMapper<DebitCard> {

	
	@Override
	public DebitCard mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		DebitCard debitCard = new DebitCard();
		
		debitCard.setAccountNo(rs.getLong("account_no"));
		debitCard.setApproved(rs.getBoolean("approved"));
		debitCard.setCardNo(rs.getLong("card_no"));
		debitCard.setCvvNo(rs.getInt("cvv_no"));
		debitCard.setPin(rs.getInt("pin"));
		return debitCard;
	}

}
