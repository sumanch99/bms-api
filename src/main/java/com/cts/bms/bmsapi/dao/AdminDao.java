package com.cts.bms.bmsapi.dao;

import java.util.List;

import com.cts.bms.bmsapi.exception.BmsException;
import com.cts.bms.bmsapi.model.Account;
import com.cts.bms.bmsapi.model.Admin;
import com.cts.bms.bmsapi.model.Branch;
import com.cts.bms.bmsapi.model.Customer;
import com.cts.bms.bmsapi.model.DebitCard;
import com.cts.bms.bmsapi.model.Employee;
import com.cts.bms.bmsapi.model.InterestRate;
import com.cts.bms.bmsapi.model.Loan;

public interface AdminDao {

	public List<Customer> viewAllCustomer();

	public List<Loan> viewAllPendingLoanRequests();

	public List<Loan> viewAllApprovedLoans();

	public boolean deleteRejectedLoanRequest(long loanId) throws BmsException;

	public boolean enableAccount(long accountNo) throws BmsException;

	public boolean disableAccount(long accountNo) throws BmsException;

	public boolean createAccount(Account account) throws BmsException;

	public boolean approveLoanRequest(long loan_id, boolean isLoanApproved) throws BmsException;

	public boolean changeInterestRate(InterestRate irate) throws BmsException;

	public boolean addBankBranch(Branch branch) throws BmsException;

	public List<Account> viewAllAccountsOfCustomer(Customer customer);

	public List<InterestRate> viewAllPlans();

	public List<DebitCard> viewAllPendingDebitCards() throws BmsException;

	public boolean approveDebitCard(DebitCard card) throws BmsException;

	public boolean deleteRejectedDebitCard(long cardNo) throws BmsException;

	public Admin addAdmin(Admin admin) throws BmsException;

	public Employee getEmployee(long empId, long adhaarNo, long phoneNo) throws BmsException;

	public Admin loadAdmin(String username);
	
	public Loan getLoan(long loanId);
}
