package com.cts.bms.bmsapi.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cts.bms.bmsapi.dto.AccountMapper;
import com.cts.bms.bmsapi.dto.CustomerMapper;
import com.cts.bms.bmsapi.dto.DebitCardMapper;
import com.cts.bms.bmsapi.dto.FixedDepositMapper;
import com.cts.bms.bmsapi.dto.LoanMapper;
import com.cts.bms.bmsapi.dto.RateValueMapper;
import com.cts.bms.bmsapi.dto.RecurringDepositMapper;
import com.cts.bms.bmsapi.exception.BmsException;
import com.cts.bms.bmsapi.model.Account;
import com.cts.bms.bmsapi.model.Customer;
import com.cts.bms.bmsapi.model.DebitCard;
import com.cts.bms.bmsapi.model.FixedDeposit;
import com.cts.bms.bmsapi.model.Loan;
import com.cts.bms.bmsapi.model.RecurringDeposit;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	private static final Logger LOGGER = LogManager.getLogger(CustomerDaoImpl.class);
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Customer loadCustomer(String username) throws BmsException {
		LOGGER.info("START");
		String query = "select * from customer where userid = ?";
		try {
			Customer customer = jdbcTemplate.queryForObject(query, new CustomerMapper(), new Object[] { username });
			LOGGER.info("END");
			return customer;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BmsException("Selection not possible");
		}
	}

	@Override
	public Customer addCustomer(Customer customer) throws BmsException {
		LOGGER.info("START");
		String query = "insert into customer (userid,password,fname,lname,gender,address,town,district,state,pin,dateofbirth,"
				+ "emailid,phonenumber,adhaarno,approved)" + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			jdbcTemplate.update(query,
					new Object[] { customer.getUserId(), customer.getPassword(), customer.getFirstName(),
							customer.getLastName(), customer.getGender(), customer.getAddress(), customer.getTown(),
							customer.getDistrict(), customer.getState(), customer.getPinCode(),
							customer.getDateOfBirth(), customer.getEmailId(), customer.getPhoneNumber(),
							customer.getAdhaarNo(), customer.isApproved()

					});
			LOGGER.info("END");
			return customer;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BmsException("Insertion not possible");
		}
	}

	@Override
	public Loan applyForLoan(Loan loan) throws BmsException {
		LOGGER.info("START");
		String query = "insert into loan (loan_code,account_no,amount,gurantor,tenure,start_date) values(?,?,?,?,?,?)";
		try {
			jdbcTemplate.update(query, new Object[] { loan.getLoanCode(), loan.getAccNo(), loan.getAmount(),
					loan.getGurantorAccNo(), loan.getTenure(), loan.getStartDate() });
			LOGGER.info("END");
			return loan;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BmsException("Insertion not possible");
		}
	}

	@Override
	public double getFdInterestRate() {
		LOGGER.info("START");
		try {
			String query = "select * from interest_rate where plan = ?";
			double rate = jdbcTemplate.queryForObject(query, new RateValueMapper(), new Object[] { "FD" });
			LOGGER.info("END");
			return rate;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			return 0;
		}

	}
	
	@Override
	public double getRdInterestRate() {
		LOGGER.info("START");
		try {
			String query = "select * from interest_rate where plan = ?";
			double rate = jdbcTemplate.queryForObject(query, new RateValueMapper(), new Object[] { "RD" });
			LOGGER.info("END");
			return rate;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			return 0;
		}

	}

	@Override
	public boolean applyForFd(FixedDeposit fixedDeposit) throws BmsException {
		LOGGER.info("START");
		String query = "insert into fixed_deposit (account_no,amount,tenure,interest,matured_amount) values(?,?,?,?,?)";
		try {
			jdbcTemplate.update(query, new Object[] { fixedDeposit.getAccount_number(), fixedDeposit.getAmount(),
					fixedDeposit.getTenure(), fixedDeposit.getInterest(), fixedDeposit.getMatured_amount() });
			LOGGER.info("END");
			return true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BmsException("Fixed Deposit not possible");
		}
	}

	@Override
	public List<FixedDeposit> getAllFixedDeposits(Account account) throws BmsException {
		LOGGER.info("START");
		String query = "select * from fixed_deposit where account_no = ?";
		try {
			LOGGER.info("END");
			return jdbcTemplate.query(query, new FixedDepositMapper(), new Object[] { account.getAccNo() });
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BmsException("Fixed Deposit selection not possible");
		}
	}
	
	
	@Override
	public boolean applyForRd(RecurringDeposit recurringDeposit) throws BmsException {
		LOGGER.info("START");
		String query = "insert into recurring_deposit (account_no,monthly_amount,tenure,interest,matured_amount) values(?,?,?,?,?)";
		try {
			jdbcTemplate.update(query, new Object[] { 
				recurringDeposit.getAccountNumber(),
				recurringDeposit.getMonthlyAmount(),
				recurringDeposit.getTenure(),
				recurringDeposit.getInterest(),
				recurringDeposit.getMaturedAmount()
			});
			LOGGER.info("END");
			return true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BmsException("Recurring Deposit not possible");
		}
	}
	
	@Override
	public List<RecurringDeposit> getAllRecurringDeposits(Account account) throws BmsException {
		LOGGER.info("START");
		String query = "select * from recurring_deposit where account_no = ?";
		try {
			LOGGER.info("END");
			return jdbcTemplate.query(query, new RecurringDepositMapper(), new Object[] { account.getAccNo() });
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BmsException("Recurring Deposit selection not possible");
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public Account getActiveAccount(long accountNo) throws BmsException {

		LOGGER.info("START");

		String query = "select * from account where account_no = ? and active = true";

		try {

			Account account = jdbcTemplate.queryForObject(query, new Object[] { accountNo }, new AccountMapper());
			LOGGER.info("END");
			return account;

		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BmsException("No Account available");
		}
	}

	@Override
	public List<Loan> viewAllApprovedLoans(String userId) {
		LOGGER.info("START");
		try {
			String sqlQuery = "select * from loan " + "where account_no in "
					+ "(select account_no from account natural join customer where userid='" + userId + "') "
					+ "and approved=true order by loan_id desc ";
			System.out.println(sqlQuery);
			List<Loan> approvedLoans = jdbcTemplate.query(sqlQuery, new LoanMapper());
			LOGGER.info("END");
			return approvedLoans;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean applyForDebitCard(DebitCard debitCard) throws BmsException {
		LOGGER.info("START");
		String query = "insert into debit_card(pin, cvv_no, account_no) values(?,?,?)";
		try {
			jdbcTemplate.update(query,
					new Object[] { debitCard.getPin(), debitCard.getCvvNo(), debitCard.getAccountNo() });
			LOGGER.info("END");
			return true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BmsException("Insertion falied");
		}
	}

	@Override
	@SuppressWarnings("deprecation")
	public List<DebitCard> viewAllApprovedDebitCards(Account account) throws BmsException {
		LOGGER.info("START");
		String query = "select * from debit_card where approved = ? and account_no = ?";
		try {
			List<DebitCard> approvedCards = jdbcTemplate.query(query, new Object[] { true, account.getAccNo() },
					new DebitCardMapper());
			LOGGER.info("END");
			return approvedCards;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BmsException("Selection falied");
		}
	}

	@Override
	@SuppressWarnings("deprecation")
	public DebitCard getDebitCard(long cardNo, int cvvNo, int pin) {
		LOGGER.info("START");
		String query = "select * from debit_card where card_no=? and pin=? and cvv_no=? and approved = ?";
		try {
			DebitCard debitCard = jdbcTemplate.queryForObject(query, new Object[] { cardNo, pin, cvvNo, true },
					new DebitCardMapper());
			LOGGER.info("END");
			return debitCard;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

	@Override
	public DebitCard getDebitCard(long cardNo) {
		LOGGER.info("START");
		String query = "select * from debit_card where card_no=?";
		try {
			DebitCard debitCard = jdbcTemplate.queryForObject(query, new DebitCardMapper(), new Object[] { cardNo,

			});
			LOGGER.info("END");
			return debitCard;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}
	
	@Override
	public List<Account> getAllAccountsOfCustomer(String userId) throws BmsException {
		LOGGER.info("START");
		String query = "select account_no,adhaar_no,account.phonenumber,account_type,balance,active,"+
		"ifsc_code,introducerAccountNo,nomineeAdhaarNo from account inner join customer on "+
				"account.adhaar_no=customer.adhaarno where customer.userid = ? and account.active = ?";
		try {
			List<Account> accounts = jdbcTemplate.query(query, new AccountMapper(),new Object[] {
					userId,
					true
			});
			LOGGER.info("END");
			return accounts;
		}catch(DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BmsException("Selection falied");
		}
	}

}
