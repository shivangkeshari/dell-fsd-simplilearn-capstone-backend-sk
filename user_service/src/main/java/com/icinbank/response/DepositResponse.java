package com.icinbank.response;

public class DepositResponse {

	private boolean depositStatus;
	private String responseMessage;
	private long acct;
	public boolean isDepositStatus() {
		return depositStatus;
	}
	public void setDepositStatus(boolean depositStatus) {
		this.depositStatus = depositStatus;
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
