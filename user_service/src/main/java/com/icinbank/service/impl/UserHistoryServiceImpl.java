package com.icinbank.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icinbank.dao.UserHistoryRepository;
import com.icinbank.model.UserHistory;
import com.icinbank.service.UserHistoryService;

@Service
public class UserHistoryServiceImpl implements UserHistoryService{

	@Autowired
	private UserHistoryRepository dao;
	
	@Override
	public UserHistory addAction(long acct, int amt, int bal, String action) {
		LocalDate today = LocalDate.now();
		
		UserHistory row=new UserHistory();
		row.setAcct(acct);
		row.setAction(action);
		row.setAmt(amt);
		row.setDate(today);
		return dao.save(row);
	}

	@Override
	public List<UserHistory> getHistory(long acct) {
				return dao.findByAccount(acct);
	}

}
