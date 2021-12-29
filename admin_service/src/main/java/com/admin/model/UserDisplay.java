package com.admin.model;

public class UserDisplay {
	
	private String fName;
	private String lName;
	private long phone;
	private String userName;
	private boolean status;
	private int featureStatus;
	private long primaryAccno;
	private int primaryBalance;
	private long savingsAccno;
	private int savingsBalance;
	
	public UserDisplay() {
		
	}

	public UserDisplay(String fName, String lName, long phone, String userName, boolean status, int featureStatus, long primaryAccno,
					   int primaryBalance, long savingsAccno, int savingsBalance) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.phone = phone;
		this.userName = userName;
		this.status = status;
		this.featureStatus = featureStatus;
		this.primaryAccno = primaryAccno;
		this.primaryBalance = primaryBalance;
		this.savingsAccno = savingsAccno;
		this.savingsBalance = savingsBalance;
	}



	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public long getPrimaryAccno() {
		return primaryAccno;
	}

	public void setPrimaryAccno(long primaryAccno) {
		this.primaryAccno = primaryAccno;
	}

	public int getPrimaryBalance() {
		return primaryBalance;
	}

	public void setPrimaryBalance(int primaryBalance) {
		this.primaryBalance = primaryBalance;
	}

	public long getSavingsAccno() {
		return savingsAccno;
	}

	public void setSavingsAccno(long savingsAccno) {
		this.savingsAccno = savingsAccno;
	}

	public int getSavingsBalance() {
		return savingsBalance;
	}

	public void setSavingsBalance(int savingsBalance) {
		this.savingsBalance = savingsBalance;
	}
	
	public int getFeatureStatus() {
		return featureStatus;
	}

	public void setFeatureStatus(int featureStatus) {
		this.featureStatus = featureStatus;
	}
	
	
	
}
