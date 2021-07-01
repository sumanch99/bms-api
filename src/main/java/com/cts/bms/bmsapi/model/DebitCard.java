package com.cts.bms.bmsapi.model;

public class DebitCard {
	private long cardNo;
	private int pin;
	private int cvvNo;
	private long accountNo;
	private boolean approved;
	
	public DebitCard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DebitCard(long cardNo, int pin, int cvvNo, long accountNo, boolean approved) {
		super();
		this.cardNo = cardNo;
		this.pin = pin;
		this.cvvNo = cvvNo;
		this.accountNo = accountNo;
		this.approved = approved;
	}
	@Override
	public String toString() {
		return "DebitCard [cardNo=" + cardNo + ", pin=" + pin + ", cvvNo=" + cvvNo + ", accountNo=" + accountNo
				+ ", approved=" + approved + "]";
	}
	public long getCardNo() {
		return cardNo;
	}
	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public int getCvvNo() {
		return cvvNo;
	}
	public void setCvvNo(int cvvNo) {
		this.cvvNo = cvvNo;
	}
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	
}
