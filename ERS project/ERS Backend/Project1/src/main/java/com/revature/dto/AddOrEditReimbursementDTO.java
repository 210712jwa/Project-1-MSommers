package com.revature.dto;

public class AddOrEditReimbursementDTO {
	
	private int reimStatus;
	
	private double reimAmount;
	
	private int reimType;
	
	private String rSub;
	
	private String reimDesc;

	public AddOrEditReimbursementDTO() {
		super();
	}

	public int getReimStatus() {
		return reimStatus;
	}

	public void setReimStatus(int reimStatus) {
		this.reimStatus = reimStatus;
	}

	public double getReimAmount() {
		return reimAmount;
	}

	public void setReimAmount(double reimAmount) {
		this.reimAmount = reimAmount;
	}

	public int getReimType() {
		return reimType;
	}

	public void setReimType(int reimType) {
		this.reimType = reimType;
	}

	public String getrSub() {
		return rSub;
	}

	public void setrSub(String rSub) {
		this.rSub = rSub;
	}

	public String getReimDesc() {
		return reimDesc;
	}

	public void setReimDesc(String reimDesc) {
		this.reimDesc = reimDesc;
	}

	
	
	
	

}
