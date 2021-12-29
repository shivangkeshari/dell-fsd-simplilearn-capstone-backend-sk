package com.icinbank.model;

public class UserDisplay {
	String userName;
	private long primaryAcctNo;
	private int primaryBalance;
	private long savingsAcctNo;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getPrimaryAcctNo() {
		return primaryAcctNo;
	}
	public void setPrimaryAcctNo(long primaryAcctNo) {
		this.primaryAcctNo = primaryAcctNo;
	}
	public int getPrimaryBalance() {
		return primaryBalance;
	}
	public void setPrimaryBalance(int primaryBalance) {
		this.primaryBalance = primaryBalance;
	}
	public long getSavingsAcctNo() {
		return savingsAcctNo;
	}
	public void setSavingsAcctNo(long savingsAcctNo) {
		this.savingsAcctNo = savingsAcctNo;
	}
	public int getSavingsBalance() {
		return savingsBalance;
	}
	public void setSavingsBalance(int savingsBalance) {
		this.savingsBalance = savingsBalance;
	}
	private int savingsBalance;
	public UserDisplay(String username, long primaryAccno, int primaryBalance, long savingsAccno, int savingsBalance) {
		super();
		this.userName = username;
		this.primaryAcctNo = primaryAccno;
		this.primaryBalance = primaryBalance;
		this.savingsAcctNo = savingsAccno;
		this.savingsBalance = savingsBalance;
	}
	
	public UserDisplay() {

	}
	
}
