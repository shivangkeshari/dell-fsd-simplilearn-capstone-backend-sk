package com.icinbank.service;

import java.util.List;

import com.icinbank.model.UserHistory;

public interface UserHistoryService {

	public UserHistory addAction(long acct,int amt,int bal,String action);
	public List<UserHistory> getHistory(long acct);
}
