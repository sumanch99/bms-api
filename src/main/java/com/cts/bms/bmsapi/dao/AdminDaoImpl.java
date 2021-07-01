package com.cts.bms.bmsapi.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cts.bms.bmsapi.dto.AccountMapper;
import com.cts.bms.bmsapi.dto.AdminMapper;
import com.cts.bms.bmsapi.dto.CustomerMapper;
import com.cts.bms.bmsapi.dto.DebitCardMapper;
import com.cts.bms.bmsapi.dto.EmployeeMapper;
import com.cts.bms.bmsapi.dto.InterestRateMapper;
import com.cts.bms.bmsapi.dto.LoanMapper;
import com.cts.bms.bmsapi.exception.BmsException;
import com.cts.bms.bmsapi.model.Account;
import com.cts.bms.bmsapi.model.Admin;
import com.cts.bms.bmsapi.model.Branch;
import com.cts.bms.bmsapi.model.Customer;
import com.cts.bms.bmsapi.model.DebitCard;
import com.cts.bms.bmsapi.model.Employee;
import com.cts.bms.bmsapi.model.InterestRate;
import com.cts.bms.bmsapi.model.Loan;

@Repository
public class AdminDaoImpl implements AdminDao {
	private static final Logger LOGGER = LogManager.getLogger(AdminDaoImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Admin loadAdmin(String empId) {
		LOGGER.info("START");
		String query = "select * from admin where emp_id = ?";
		try {
			Admin admin = jdbcTemplate.queryForObject(query, new AdminMapper(), new Object[] { empId });
			LOGGER.info("END");
			return admin;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Admin addAdmin(Admin admin) throws BmsException {
		LOGGER.info("START");
		String query = "insert into admin (emp_id,password,adhaar_no,phone_number)" + " values(?,?,?,?)";
		try {
			jdbcTemplate.update(query,
					new Object[] { admin.getEmpId(), admin.getPassword(), admin.getAdhaarNo(), admin.getPhoneNo() });
			LOGGER.info("END");
			return admin;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BmsException("Insertion not possible");
		}
	}

	@Override
	@SuppressWarnings("deprecation")
	public Employee getEmployee(long empId, long adhaarNo, long phoneNo) throws BmsException {
		LOGGER.info("START");
		String query = "select * from employee where emp_id=? and adhaar_no = ? and phone_number=?";
		try {
			Employee emp = jdbcTemplate.queryForObject(query, new Object[] { empId, adhaarNo, phoneNo },
					new EmployeeMapper());
			LOGGER.info("END");
			return emp;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BmsException("Selection not possible");
		}
	} 
 
	@Override
	public List<Customer> viewAllCustomer() {
		LOGGER.info("START");
		try {
			String sqlQuery = "select * from customer where approved=1 order by fname,lname";
			List<Customer> customers = jdbcTemplate.query(sqlQuery, new CustomerMapper());
			LOGGER.info("END");
			return customers;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean createAccount(Account account) throws BmsException {
		LOGGER.info("START");
		String query = "insert into account (adhaar_no,account_type,active,ifsc_code,phonenumber,introducerAccountNo,nomineeAdhaarNo)"
				+ " values(?,?,?,?,?,?,?)";
		try {
			jdbcTemplate.update(query,
					new Object[] { account.getAdhaarNumber(), account.getAccType(), account.isActive(),
							account.getIfscCode(), account.getPhoneNumber(), account.getIntroducerAccountNo(),
							account.getNomineeAdhaarNo() });
			LOGGER.info("END");
			return true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BmsException("Insertion not possible");
		}
	}

	@Override
	public boolean enableAccount(long accountNo) throws BmsException {
		LOGGER.info("START");
		String sqlQuery = "update account set active = true where account_no =" + accountNo;
		try {
			jdbcTemplate.update(sqlQuery);
			LOGGER.info("END");
			return true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BmsException("Updation not possible");
		}
	}

	@Override
	public boolean disableAccount(long accountNo) throws BmsException {
		LOGGER.info("START");
		String sqlQuery = "update account set active = false where account_no =" + accountNo;
		try {
			jdbcTemplate.update(sqlQuery);
			LOGGER.info("END");
			return true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BmsException("Updation not possible");
		}
	}

	@Override
	public boolean approveLoanRequest(long loanId, boolean isLoanApproved) throws BmsException {
		LOGGER.info("START");
		String sqlQuery = "update loan set approved = ? where loan_id = ? ";

		try {
			jdbcTemplate.update(sqlQuery, new Object[] { isLoanApproved, loanId });
			LOGGER.info("END");
			return true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BmsException("Approve not Possible");
		}
	}

	@Override
	public boolean changeInterestRate(InterestRate irate) throws BmsException {
		LOGGER.info("START");
		String sqlQuery = "update interest_rate set rate = ? where plan=?";
		System.out.println(irate);
		try {

			jdbcTemplate.update(sqlQuery, new Object[] { irate.getRate(), irate.getPlan() });
			LOGGER.info("END");
			return true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BmsException("Provide appropriate value");
		}
 
	}

	@Override
	public boolean addBankBranch(Branch branch) throws BmsException {
		LOGGER.info("START");
		String query = "insert into branch (ifsc_code,branch_name,address,pincode,branch_fund)" + " values(?,?,?,?,?)";
		try {
			jdbcTemplate.update(query, new Object[] { branch.getIfscCode(), branch.getBranchName(), branch.getAddress(),
					branch.getPincode(), branch.getBranchFund() });
			LOGGER.info("END");
			return true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BmsException("Insertion not possible");
		}
	}

	@Override
	public List<Loan> viewAllPendingLoanRequests() {
		LOGGER.info("START");
		try {
			String sqlQuery = "select * from loan where approved = 0 order by loan_id desc";
			List<Loan> pendingLoans = jdbcTemplate.query(sqlQuery, new LoanMapper());
			LOGGER.info("END");
			return pendingLoans;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

	@Override
	public List<Loan> viewAllApprovedLoans() {
		LOGGER.info("START");
		try {
			String sqlQuery = "select * from loan where approved = 1 order by loan_id desc";
			List<Loan> loans = jdbcTemplate.query(sqlQuery, new LoanMapper());
			LOGGER.info("END");
			return loans;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			return null;
		}

	}

	@Override
	public Loan getLoan(long loanId) {
		LOGGER.info("START");
		try {
			System.out.println(loanId);
			String sqlQuery = "select * from loan where loan_id = " + loanId;
			Loan loan = jdbcTemplate.queryForObject(sqlQuery, new LoanMapper());
			LOGGER.info("END");
			return loan;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean deleteRejectedLoanRequest(long loanId) throws BmsException {
		LOGGER.info("START");
		String query = "delete from loan where loan_id = " + loanId;
		try {
			jdbcTemplate.update(query);
			LOGGER.info("END");
			return true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BmsException("Deletion of loan request not possible");
		}
	}

	@Override
	public List<Account> viewAllAccountsOfCustomer(Customer customer) {
		LOGGER.info("START");
		try {
			String sqlQuery = "select * from account where adhaar_no = " + customer.getAdhaarNo();
			List<Account> accounts = jdbcTemplate.query(sqlQuery, new AccountMapper());
			LOGGER.info("END");
			return accounts;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

	@Override
	public List<InterestRate> viewAllPlans() {
		LOGGER.info("START");
		try {
			String query = "select * from interest_rate";
			List<InterestRate> plans = jdbcTemplate.query(query, new InterestRateMapper());
			LOGGER.info("END");
			return plans;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

	public List<DebitCard> viewAllPendingDebitCards() throws BmsException {
		LOGGER.info("START");
		String query = "select * from debit_card where approved = " + false + " order by card_no desc";
		try {
			List<DebitCard> pendingCards = jdbcTemplate.query(query, new DebitCardMapper());
			LOGGER.info("END");
			return pendingCards;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BmsException("Selection failed");
		}
	}

	public boolean approveDebitCard(DebitCard card) throws BmsException {
		LOGGER.info("START");
		String query = "update debit_card set approved = ? where card_no = ?";
		try {
			jdbcTemplate.update(query, new Object[] { true, card.getCardNo() });
			LOGGER.info("END");
			return true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BmsException("Updation failed");
		}
	}

	public boolean deleteRejectedDebitCard(long cardNo) throws BmsException {
		LOGGER.info("START");
		String query = "delete from debit_card where card_no = ?";
		try {
			jdbcTemplate.update(query, new Object[] { cardNo });
			LOGGER.info("END");
			return true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BmsException("Deletion failed");
		}
	}

}
