package com.icinbank.details;

public class TransferDetails {
	
	private long sAccount;
	private long rAccount;
	private int amt;
	private String userName;
	private String ifsc;
	
	
	
	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public long getsAccount() {
		return sAccount;
	}

	public void setsAccount(long sAccount) {
		this.sAccount = sAccount;
	}

	public long getrAccount() {
		return rAccount;
	}

	public void setrAccount(long rAccount) {
		this.rAccount = rAccount;
	}

	public int getAmt() {
		return amt;
	}
	
	public void setAmt(int amt) {
		this.amt = amt;
	}

	
}
