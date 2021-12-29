package com.admin.service;

import java.util.List;

import com.admin.model.ChequebookRequest;
import com.admin.model.Transfer;
import com.admin.model.User;
import com.admin.model.UserDisplay;

public interface AdminService {
	
	public List<UserDisplay> getAllUsers();
	public List<Transfer> getAllTransactions(long accountNo);
	public List<ChequebookRequest> getAllChequebookRequests();
	public void enableUser(String userName);
	public void disableUser(String userName);
	public void authorizeUser(String userName);
	public void cancelAuthorization(String userName);
	public List<User> getAllUnauthorizedUsers();
	public void setUserFeatures(String userName, int featureId);
	public UserDisplay searchUser(String userDetail);
	void acceptChequebookRequest(long accNo);
	
}
