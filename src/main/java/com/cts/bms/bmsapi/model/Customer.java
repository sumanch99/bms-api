package com.cts.bms.bmsapi.model;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;

public class Customer {
	@NotNull(message = "UserId is required")
	private String userId;
	@NotNull(message = "password is required")
	private String password;
	@NotNull(message = "first name is required")
	private String firstName;
	private String lastName;
	@NotNull(message = "gender is required")
	private String gender;
	@NotNull(message = "address is required")
	private String address;
	@NotNull(message = "town is required")
	private String town;
	@NotNull(message = "district is required")
	private String district;
	@NotNull(message = "state is required")
	private String state;
	@NotNull(message = "pin code is required")
	@Min(value = 700001)
	@Max(value = 799999)
	private int pinCode;
	@NotNull(message = "date of birth is required")
	private Date dateOfBirth;
	@NotNull(message = "email id is required")
	private String emailId;
	@NotNull(message = "phone number is required")
	private String phoneNumber;
	@NotNull(message = "adhaar number is required")
	private String adhaarNo;
	private boolean approved;
	public Customer() {
		super();
	}
	public Customer(@NotNull(message = "UserId is required") String userId,
			@NotNull(message = "password is required") String password,
			@NotNull(message = "first name is required") String firstName, String lastName,
			@NotNull(message = "gender is required") String gender,
			@NotNull(message = "address is required") String address,
			@NotNull(message = "town is required") String town,
			@NotNull(message = "district is required") String district,
			@NotNull(message = "state is required") String state,
			@NotNull(message = "pin code is required") @Min(700001) @Max(799999) int pinCode,
			@NotNull(message = "date of birth is required") Date dateOfBirth,
			@NotNull(message = "email id is required") String emailId,
			@NotNull(message = "phone number is required") String phoneNumber,
			@NotNull(message = "adhaar number is required") String adhaarNo, boolean approved) {
		super();
		this.userId = userId;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.address = address;
		this.town = town;
		this.district = district;
		this.state = state;
		this.pinCode = pinCode;
		this.dateOfBirth = dateOfBirth;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.adhaarNo = adhaarNo;
		this.approved = approved;
	}
	
	@Override
	public String toString() {
		return "Customer [userId=" + userId + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", address=" + address + ", town=" + town + ", district=" + district
				+ ", state=" + state + ", pinCode=" + pinCode + ", dateOfBirth=" + dateOfBirth + ", emailId=" + emailId
				+ ", phoneNumber=" + phoneNumber + ", adhaarNo=" + adhaarNo + ", approved=" + approved + "]";
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPinCode() {
		return pinCode;
	}
	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAdhaarNo() {
		return adhaarNo;
	}
	public void setAdhaarNo(String adhaarNo) {
		this.adhaarNo = adhaarNo;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	
	

}
