package com.cts.bms.bmsapi.model;

import java.util.Date;

public class RecurringDeposit {
	private int recurringDepositId;
	private double monthlyAmount;
	private int tenure;
	private long accountNumber;
	private double interest;
	private Date startDate;
	private double maturedAmount;
	public RecurringDeposit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RecurringDeposit(int recurringDepositId, double monthlyAmount, int tenure, long accountNumber,
			double interest, Date startDate, double maturedAmount) {
		super();
		this.recurringDepositId = recurringDepositId;
		this.monthlyAmount = monthlyAmount;
		this.tenure = tenure;
		this.accountNumber = accountNumber;
		this.interest = interest;
		this.startDate = startDate;
		this.maturedAmount = maturedAmount;
	}
	@Override
	public String toString() {
		return "RecurringDeposit [recurringDepositId=" + recurringDepositId + ", monthlyAmount=" + monthlyAmount
				+ ", tenure=" + tenure + ", accountNumber=" + accountNumber + ", interest=" + interest + ", startDate="
				+ startDate + ", maturedAmount=" + maturedAmount + "]";
	}
	public int getRecurringDepositId() {
		return recurringDepositId;
	}
	public void setRecurringDepositId(int recurringDepositId) {
		this.recurringDepositId = recurringDepositId;
	}
	public double getMonthlyAmount() {
		return monthlyAmount;
	}
	public void setMonthlyAmount(double monthlyAmount) {
		this.monthlyAmount = monthlyAmount;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public double getMaturedAmount() {
		return maturedAmount;
	}
	public void setMaturedAmount(double maturedAmount) {
		this.maturedAmount = maturedAmount;
	}
	
}
