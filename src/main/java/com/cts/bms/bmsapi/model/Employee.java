package com.cts.bms.bmsapi.model;

public class Employee {
	private long empId;
	private long adhaarNo;
	private long phoneNo;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(long empId, long adhaarNo, long phoneNo) {
		super();
		this.empId = empId;
		this.adhaarNo = adhaarNo;
		this.phoneNo = phoneNo;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", adhaarNo=" + adhaarNo + ", phoneNo=" + phoneNo + "]";
	}
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	public long getAdhaarNo() {
		return adhaarNo;
	}
	public void setAdhaarNo(long adhaarNo) {
		this.adhaarNo = adhaarNo;
	}
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	
}
