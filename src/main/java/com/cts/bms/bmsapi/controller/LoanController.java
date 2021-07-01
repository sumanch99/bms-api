package com.cts.bms.bmsapi.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.bms.bmsapi.model.Loan;
import com.cts.bms.bmsapi.response.CustomJsonResponse;
import com.cts.bms.bmsapi.service.AdminService;
import com.cts.bms.bmsapi.service.LoanService;

@RestController
@RequestMapping("/customer")
public class LoanController {
	private static final Logger logger=LogManager.getLogger( LoanController.class);
	@Autowired
	LoanService service;
	
	@Autowired
	AdminService adminService;
	
	@PostMapping("/apply-loan")
	public ResponseEntity<Object> applyForLoan(@RequestBody Loan loan) {
		logger.info("START");
		if(service.applyForLoan(loan)) {
			logger.info("END");
			return CustomJsonResponse.generateResponse("Applying for loan successful", HttpStatus.OK, loan);
		}
		logger.error("Applying for loan not possible");
		return CustomJsonResponse.generateResponse("Applying for loan not possible", HttpStatus.EXPECTATION_FAILED, loan);
	}
	
	@GetMapping("/view-my-loans/{userId}") 
	public ResponseEntity<Object> getAllLoans(@PathVariable String userId) {
		logger.info("START");
		System.out.println(userId);
		List<Loan> loans = adminService.getAllApprovedLoans(userId);
		logger.info("END");
		System.out.println(loans);
		return CustomJsonResponse.generateResponse("All loans", HttpStatus.OK,loans);
	}
}
