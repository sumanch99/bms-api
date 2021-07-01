package com.cts.bms.bmsapi.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.bms.bmsapi.response.CustomJsonResponse;

@RestController
public class LoginController {
	private static final Logger LOGGER=LogManager.getLogger(LoginController.class);	
	
	@GetMapping("/admin/log-in")
	public ResponseEntity<Object> adminLogin() {
		LOGGER.info("ADMIN LOGIN");
		return CustomJsonResponse.generateResponse("Login successfull", HttpStatus.OK, null);
	}
	@GetMapping("/customer/log-in")
	public ResponseEntity<Object> customerLogin() {
		LOGGER.info("CUSTOMER LOGIN");
		return CustomJsonResponse.generateResponse("Login successfull", HttpStatus.OK, null);
	}
	
}
