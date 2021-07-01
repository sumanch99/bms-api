package com.cts.bms.bmsapi.model;

import java.util.Date;

public class Loan {
	private long loan_id;
	private String loanCode;
	private long accNo;
	private double amount;
	private long gurantorAccNo;
	private int tenure;
	private Date startDate;
	private boolean approved;
	
	public Loan() {
		super();
	}

	public Loan(long loan_id, String loanCode, long accNo, double amount, long gurantorAccNo, int tenure,
			Date startDate, boolean approved) {
		super();
		this.loan_id = loan_id;
		this.loanCode = loanCode;
		this.accNo = accNo;
		this.amount = amount;
		this.gurantorAccNo = gurantorAccNo;
		this.tenure = tenure;
		this.startDate = startDate;
		this.approved = approved;
	}

	@Override
	public String toString() {
		return "Loan [loan_id=" + loan_id + ", loanCode=" + loanCode + ", accNo=" + accNo + ", amount=" + amount
				+ ", gurantorAccNo=" + gurantorAccNo + ", tenure=" + tenure + ", startDate=" + startDate + ", approved="
				+ approved + "]";
	}

	public long getLoan_id() {
		return loan_id;
	}

	public void setLoan_id(long loan_id) {
		this.loan_id = loan_id;
	}

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	public long getAccNo() {
		return accNo;
	}

	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getGurantorAccNo() {
		return gurantorAccNo;
	}

	public void setGurantorAccNo(long gurantorAccNo) {
		this.gurantorAccNo = gurantorAccNo;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	
}
