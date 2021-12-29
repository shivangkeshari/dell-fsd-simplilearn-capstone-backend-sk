package com.icinbank.response;

public class WithdrawResponse {

	private boolean withdrawStatus;
	private String responseMessage;
	private long acct;
	
	public boolean isWithdrawStatus() {
		return withdrawStatus;
	}
	public void setWithdrawStatus(boolean withdrawStatus) {
		this.withdrawStatus = withdrawStatus;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public long getAcct() {
		return acct;
	}
	public void setAcct(long acct) {
		this.acct = acct;
	}
	

	
}
