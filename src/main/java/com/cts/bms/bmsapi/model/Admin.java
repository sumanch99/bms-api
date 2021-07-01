package com.cts.bms.bmsapi.model;
import javax.validation.constraints.NotNull;
public class Admin {
	@NotNull(message="emp id is required")
   private long empId;
	@NotNull(message="password is required")
   private String password;
	private long adhaarNo;
	private long phoneNo;
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(@NotNull(message = "emp id is required") long empId,
			@NotNull(message = "password is required") String password, long adhaarNo, long phoneNo) {
		super();
		this.empId = empId;
		this.password = password; 
		this.adhaarNo = adhaarNo; 
		this.phoneNo = phoneNo;
	}
	@Override
	public String toString() {
		return "Admin [empId=" + empId + ", password=" + password + ", adhaarNo=" + adhaarNo + ", phoneNo=" + phoneNo
				+ "]";
	}
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
   
