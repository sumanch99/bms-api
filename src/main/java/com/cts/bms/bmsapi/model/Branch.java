package com.cts.bms.bmsapi.model;

public class Branch {
	private String ifscCode;
	private String branchName;
	private String address;
	private int pincode;
	private double branchFund;

	public Branch() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Branch(String ifscCode, String branchName, String address, int pincode, double branchFund) {
		super();
		this.ifscCode = ifscCode;
		this.branchName = branchName;
		this.address = address;
		this.pincode = pincode;
		this.branchFund = branchFund;
	}

	@Override
	public String toString() {
		return "Branch [ifscCode=" + ifscCode + ", branchName=" + branchName + ", address=" + address + ", pincode="
				+ pincode + ", branchFund=" + branchFund + "]";
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public double getBranchFund() {
		return branchFund;
	}

	public void setBranchFund(double branchFund) {
		this.branchFund = branchFund;
	}

}
