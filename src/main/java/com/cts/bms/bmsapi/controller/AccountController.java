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

import com.cts.bms.bmsapi.exception.BmsException;
import com.cts.bms.bmsapi.model.Account;
import com.cts.bms.bmsapi.model.Transaction;
import com.cts.bms.bmsapi.response.CustomJsonResponse;
import com.cts.bms.bmsapi.service.AccountService;
import com.cts.bms.bmsapi.service.CustomerService;
import com.cts.bms.bmsapi.service.TransactionService;

@RestController
@RequestMapping("/customer")
public class AccountController {
	private static final Logger LOGGER=LogManager.getLogger(AccountController.class);
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	AccountService service;
	
	
	@Autowired
	CustomerService customerService;

	@GetMapping("/get-balance/{accountNo}")
	public ResponseEntity<Object> getBalance(@PathVariable long accountNo) {

		Account account = service.checkBalance(accountNo);
		LOGGER.info("START");
		if (account == null) {
			LOGGER.warn("BAD REQUEST");
			return CustomJsonResponse.generateResponse("Account not found", HttpStatus.NOT_FOUND, null);
		}
		LOGGER.info("END");
		return CustomJsonResponse.generateResponse("Account balance", HttpStatus.OK, account.getBalance());
		
	}

	@PostMapping("/deposit/{accountNo}")
	public ResponseEntity<Object> depositToAccount(@PathVariable long accountNo,@RequestBody double amount) {

		Account account = service.checkBalance(accountNo);
		LOGGER.info("START");
		if (account == null) {
			LOGGER.error("BAD REQUEST");
			return CustomJsonResponse.generateResponse("Account not found", HttpStatus.NOT_FOUND, null);
		}
		
		if(service.depositIntoAccount(account, amount)) {
			LOGGER.info("END");
			return CustomJsonResponse.generateResponse("Amount deposited successfully", HttpStatus.OK, amount);
		}
		LOGGER.error("Amount cannot be deposited");
		return CustomJsonResponse.generateResponse("Amount cannot be deposited", HttpStatus.CONFLICT, amount);
	}
	
	@PostMapping("/withdraw/{accountNo}")
	public ResponseEntity<Object> withdrawFromAccount(@PathVariable long accountNo,@RequestBody double amount) {

		Account account = service.checkBalance(accountNo);
		LOGGER.info("START");
		if (account == null) {
			LOGGER.warn("BAD REQUEST");
			return CustomJsonResponse.generateResponse("Account not found", HttpStatus.NOT_FOUND, null);
		}
		
		if(service.withdrawFromAccount(account, amount)) {
			LOGGER.info("END");
			return CustomJsonResponse.generateResponse("Amount withdrawn successfully", HttpStatus.OK, amount);
		}
		LOGGER.error("Insufficient balance");
		return CustomJsonResponse.generateResponse("Insufficient balance", HttpStatus.CONFLICT, amount);
	}
	
	@PostMapping("/account-transfer/{fromAccountNo}/{toAccountNo}")
	public ResponseEntity<Object> accountToAccountTransfer(@PathVariable long fromAccountNo,@PathVariable long toAccountNo,@RequestBody double amount) {
		try {
			LOGGER.info("START");
			if(service.accountToAccountTransfer(fromAccountNo, toAccountNo, amount)) {
				LOGGER.info("END");
				return CustomJsonResponse.generateResponse("Amount sent successfully", HttpStatus.OK, amount);
			}
		} catch (BmsException e) {
			LOGGER.error(e.getMessage());
			return CustomJsonResponse.generateResponse(e.getMessage(), HttpStatus.CONFLICT, amount);
		}LOGGER.error("Account not found");
		return CustomJsonResponse.generateResponse("Account not found", HttpStatus.NOT_FOUND, amount);
	}
	
	@GetMapping("/view-statement/{accountNo}") 
	public ResponseEntity<Object> getAllTransactionsForAccount(@PathVariable long accountNo) {
		LOGGER.info("START");
		List<Transaction> transactions = transactionService.viewAllTransactions(accountNo);
		if(transactions!=null) {
			LOGGER.info("END");
			return CustomJsonResponse.generateResponse("All transactions", HttpStatus.OK,transactions);
		}
		LOGGER.error("Transactions cannot be fetched");
		return CustomJsonResponse.generateResponse("Transactions cannot be fetched", HttpStatus.CONFLICT,transactions);
	}
	
	@GetMapping("/get-all-accounts/{userId}")
	public ResponseEntity<Object> getAllAccounts(@PathVariable String userId) {
		LOGGER.info("START");
		List<Account> accounts = customerService.getAllAccounts(userId);
		if(accounts!=null) {
			LOGGER.info("END");
			return CustomJsonResponse.generateResponse("All accounts of an user", HttpStatus.OK,accounts);
		}
		LOGGER.error("Accounts cannot be fetched");
		return CustomJsonResponse.generateResponse("Accounts cannot be fetched", HttpStatus.CONFLICT,accounts);
	}
}
