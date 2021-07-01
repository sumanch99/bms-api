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

import com.cts.bms.bmsapi.model.FixedDeposit;
import com.cts.bms.bmsapi.model.RecurringDeposit;
import com.cts.bms.bmsapi.response.CustomJsonResponse;
import com.cts.bms.bmsapi.service.PlanService;

@RestController
@RequestMapping("/customer")
public class DepositController {
	private static final Logger LOGGER=LogManager.getLogger(DepositController.class);	
	@Autowired
	PlanService service;
	
	@PostMapping("/apply-fd")
	public ResponseEntity<Object> applyForFd(@RequestBody FixedDeposit fd) {
		LOGGER.info("START");
		if(service.generateFixedDeposit(fd)) {
			LOGGER.info("END");
			return CustomJsonResponse.generateResponse("Fixed deposit generated successfully", HttpStatus.OK, fd);
		}
		LOGGER.warn("BAD-REQUEST");
		return CustomJsonResponse.generateResponse("Fixed deposit cannot be generated.", HttpStatus.BAD_REQUEST, null);
	}
	
	@GetMapping("/view-my-fd/{accountNo}")
	public ResponseEntity<Object> viewMyFd(@PathVariable long accountNo) {
		LOGGER.info("START");
		List<FixedDeposit> fds = service.getAllFixedDeposits(accountNo);
		if(fds!=null) {
			LOGGER.info("END");
			return CustomJsonResponse.generateResponse("Fixed deposits", HttpStatus.OK, fds);
		}
		LOGGER.warn("BAD-REQUEST");
		return CustomJsonResponse.generateResponse("Fixed deposits not found", HttpStatus.NOT_FOUND, null);
	}
	
	@PostMapping("/apply-rd")
	public ResponseEntity<Object> applyForRd(@RequestBody RecurringDeposit rd) {
		LOGGER.info("START");
		if(service.generateRecurringDeposit(rd)) {
			LOGGER.info("END");
			return CustomJsonResponse.generateResponse("Recurring deposit generated successfully", HttpStatus.OK, rd);
		}
		LOGGER.warn("BAD-REQUEST");
		return CustomJsonResponse.generateResponse("Recurring deposit cannot be generated.", HttpStatus.BAD_REQUEST, null);
	}
	
	@GetMapping("/view-my-rd/{accountNo}")
	public ResponseEntity<Object> viewMyRd(@PathVariable long accountNo) {
		LOGGER.info("START");
		List<RecurringDeposit> rds = service.getAllRecurringDeposits(accountNo);
		if(rds!=null) {
			LOGGER.info("END");
			return CustomJsonResponse.generateResponse("Recurring deposits", HttpStatus.OK, rds);
		}
		LOGGER.warn("BAD-REQUEST");
		return CustomJsonResponse.generateResponse("Recurring deposits not found", HttpStatus.NOT_FOUND, null);
	}
}
