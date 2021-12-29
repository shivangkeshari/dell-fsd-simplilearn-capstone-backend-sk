package com.icinbank.response;

public class RegisterResponse {

	private boolean registrationStatus;
	private String responseMessage;
	private String userName;
	
	public boolean getRegistrationStatus() {
		return registrationStatus;
	}
	public void setRegistrationStatus(boolean registrationStatus) {
		this.registrationStatus = registrationStatus;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
