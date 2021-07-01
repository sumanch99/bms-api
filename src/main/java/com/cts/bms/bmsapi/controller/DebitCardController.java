package com.cts.bms.bmsapi.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.bms.bmsapi.exception.AccountNotFoundException;
import com.cts.bms.bmsapi.model.DebitCard;
import com.cts.bms.bmsapi.response.CustomJsonResponse;
import com.cts.bms.bmsapi.service.CardService;

@RestController
public class DebitCardController { 
private static final Logger logger=LogManager.getLogger(DebitCardController.class);
	
	@Autowired
	CardService cardService;
	
	@PostMapping("/customer/apply-debit-card")
	public ResponseEntity<Object> changeInterestRate(@RequestBody DebitCard card ) {
		logger.info("START");
		try {
			if(cardService.applyForDebitCard(card)) {
				logger.info("END");
				return CustomJsonResponse.generateResponse("Applied for Debit Card successfully", HttpStatus.OK, card);
			}
		} catch (AccountNotFoundException e) {
			logger.error("Account not found");
			return CustomJsonResponse.generateResponse("Account not found", HttpStatus.NOT_FOUND, card);
		}
		logger.error("DebitCard application not possible");
		return CustomJsonResponse.generateResponse("DebitCard application not possible", HttpStatus.EXPECTATION_FAILED, card);
	}
	
	@PutMapping("/admin/approve-debit-card/{cardNo}")
	public ResponseEntity<Object> approveDebitCard(@PathVariable long cardNo) {
		logger.info("START");
		if(cardService.approveDebitCard(cardNo)) {
			logger.info("END");
			return CustomJsonResponse.generateResponse("Approved Debit Card successfully", HttpStatus.OK, cardNo);
		}
		logger.error("DebitCard approval failed");
		return CustomJsonResponse.generateResponse("DebitCard approval failed", HttpStatus.EXPECTATION_FAILED, cardNo);
	} 
	  
	@DeleteMapping("/admin/reject-debit-card/{cardNo}")
	public ResponseEntity<Object> rejectDebitCard(@PathVariable long cardNo) {
		logger.info("START");
		if(cardService.rejectDebitCard(cardNo)) {
			logger.info("END");
			return CustomJsonResponse.generateResponse("Rejected Debit Card successfully", HttpStatus.OK, cardNo);
		}
		logger.error("DebitCard rejection failed");
		return CustomJsonResponse.generateResponse("DebitCard rejection failed", HttpStatus.EXPECTATION_FAILED, cardNo);
	}    
	
	@GetMapping("/admin/view-all-pending-debit-cards")
	public ResponseEntity<Object> viewAllPendingDebitCards() {
		logger.info("START");
		List<DebitCard> cards = cardService.viewAllPendingDebitCards();
		if(cards!=null) {
			logger.info("END");
			return CustomJsonResponse.generateResponse("Pending Debit Cards", HttpStatus.OK, cards);
		}
		logger.warn("Pending Debit Cards cannot be fetched");
		return CustomJsonResponse.generateResponse("Pending Debit Cards cannot be fetched", HttpStatus.CONFLICT, null );
	}
	
	@GetMapping("/customer/view-debit-card/{accountNo}")
	public ResponseEntity<Object> viewDebitCard(@PathVariable long accountNo) {
		logger.info("START");
		List<DebitCard> cards;
		try {
			cards = cardService.getDebitCard(accountNo);
		} catch (AccountNotFoundException e) {
			logger.warn("BAD REQUEST");
			return CustomJsonResponse.generateResponse("Account number not found", HttpStatus.NOT_FOUND, null );
		}
		if(cards!=null) {
			logger.info("END");
			return CustomJsonResponse.generateResponse(" Debit Cards", HttpStatus.OK, cards);
		}
		logger.warn("BAD REQUEST");
		return CustomJsonResponse.generateResponse("Debit Cards cannot be fetched", HttpStatus.CONFLICT, null );
	}
}
