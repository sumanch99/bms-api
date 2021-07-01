package com.cts.bms.bmsapi.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.bms.bmsapi.model.InterestRate;
import com.cts.bms.bmsapi.response.CustomJsonResponse;
import com.cts.bms.bmsapi.service.AdminService;
import com.cts.bms.bmsapi.service.PlanService;

@RestController
@RequestMapping("/admin")
public class InterestController {
private static final Logger logger=LogManager.getLogger(InterestController.class);
	
	@Autowired
	AdminService service;
	
	@Autowired
	PlanService planService;
	
	@PostMapping("/change-interest-rate")
	public ResponseEntity<Object> changeInterestRate(@RequestBody InterestRate irate ) {
		logger.info("START");
		if(service.changeInterestRate(irate)) {
			logger.info("END");
			return CustomJsonResponse.generateResponse("Interest rate changed", HttpStatus.OK, irate);
		}
		logger.error("Interest rate change unsuccessfull");
		return CustomJsonResponse.generateResponse("Interest rate change unsuccessfull", HttpStatus.EXPECTATION_FAILED, irate);
	}
	
	@GetMapping("/view-interest-plans")
	public ResponseEntity<Object> viewAllPlans() {
		logger.info("START");
		List<InterestRate> plans = planService.viewAllPlans();
		if(plans.isEmpty()) {
			logger.warn("BAD REQUEST");
			return CustomJsonResponse.generateResponse("No Plans available", HttpStatus.OK,null);
		}
		logger.info("END");
		return CustomJsonResponse.generateResponse("Plans fetched", HttpStatus.OK, plans);
	}
}
