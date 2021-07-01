package com.cts.bms.bmsapi.model;

public class InterestRate {
	private String plan;
	private double rate;
	public InterestRate() {
		super();
	}
	public InterestRate(String plan, double rate) {
		super();
		this.plan = plan;
		this.rate = rate;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	@Override
	public String toString() {
		return "InterestRate [plan=" + plan + ", rate=" + rate + "]";
	}
	
	
}
