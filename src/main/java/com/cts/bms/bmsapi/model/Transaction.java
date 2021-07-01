package com.cts.bms.bmsapi.model;

public class Transaction {
	private long fromAccount;
	private long toAccount;
	private double amount;
	private String date;
	private boolean flag;
	private boolean atmFlag;
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(long fromAccount, long toAccount, double amount, String date, boolean flag, boolean atmFlag) {
		super();
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
		this.date = date;
		this.flag = flag;
		this.atmFlag = atmFlag;
	}

	@Override
	public String toString() {
		return "Transaction [fromAccount=" + fromAccount + ", toAccount=" + toAccount + ", amount=" + amount + ", date="
				+ date + ", flag=" + flag + ", atmFlag=" + atmFlag + "]";
	}

	public long getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(long fromAccount) {
		this.fromAccount = fromAccount;
	}

	public long getToAccount() {
		return toAccount;
	}

	public void setToAccount(long toAccount) {
		this.toAccount = toAccount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isAtmFlag() {
		return atmFlag;
	}

	public void setAtmFlag(boolean atmFlag) {
		this.atmFlag = atmFlag;
	}

	
	
	
	
}
