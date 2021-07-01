package com.cts.bms.bmsapi.model;

public class Account {
	
	private long accNo;
	private String accType;
	private double balance;
	private boolean active;
	private String ifscCode;
	private long adhaarNumber;
	private String phoneNumber;
	private long introducerAccountNo;
	private String nomineeAdhaarNo;
	
	
	
	@Override
	public String toString() {
		return "Account [accNo=" + accNo + ", accType=" + accType + ", balance=" + balance + ", active=" + active
				+ ", ifscCode=" + ifscCode + ", adhaarNumber=" + adhaarNumber + ", phoneNumber=" + phoneNumber
				+ ", introducerAccountNo=" + introducerAccountNo + ", nomineeAdhaarNo=" + nomineeAdhaarNo + "]";
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(long accNo, String accType, double balance, boolean active, String ifscCode, long adhaarNumber,
			String phoneNumber, long introducerAccountNo, String nomineeAdhaarNo) {
		super();
		this.accNo = accNo;
		this.accType = accType;
		this.balance = balance;
		this.active = active;
		this.ifscCode = ifscCode;
		this.adhaarNumber = adhaarNumber;
		this.phoneNumber = phoneNumber;
		this.introducerAccountNo = introducerAccountNo;
		this.nomineeAdhaarNo = nomineeAdhaarNo;
	}

	public long getAccNo() {
		return accNo;
	}

	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public long getAdhaarNumber() {
		return adhaarNumber;
	}

	public void setAdhaarNumber(long adhaarNumber) {
		this.adhaarNumber = adhaarNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getIntroducerAccountNo() {
		return introducerAccountNo;
	}

	public void setIntroducerAccountNo(long introducerAccountNo) {
		this.introducerAccountNo = introducerAccountNo;
	}

	public String getNomineeAdhaarNo() {
		return nomineeAdhaarNo;
	}

	public void setNomineeAdhaarNo(String nomineeAdhaarNo) {
		this.nomineeAdhaarNo = nomineeAdhaarNo;
	}
	
	
	
}
