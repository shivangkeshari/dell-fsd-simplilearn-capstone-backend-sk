package com.icinbank.response;

public class TransferResponse {

	private boolean transferStatus;
	private String responseMessage;
	private long sAccount;
	
	public boolean isTransferStatus() {
		return transferStatus;
	}
	public void setTransferStatus(boolean transferStatus) {
		this.transferStatus = transferStatus;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public long getsAccount() {
		return sAccount;
	}
	public void setsAccount(long sAccount) {
		this.sAccount = sAccount;
	}
	
	
}
