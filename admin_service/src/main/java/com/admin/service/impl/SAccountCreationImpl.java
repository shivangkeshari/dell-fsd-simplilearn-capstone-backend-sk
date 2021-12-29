package com.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.dao.SaccountRepository;
import com.admin.dao.UserRepository;
import com.admin.model.SAccount;

@Service
public class SAccountCreationImpl {
	
	@Autowired
	SaccountRepository dao;
	
	@Autowired
	UserRepository udao;
	
	private final String bankCode = "3914";
	private final String countryCode = "91";
	private final String branchCode = "820";
	private final String accountcode="2";
	
	public long generate_saving(int userId) {
		String accNo = bankCode+countryCode+branchCode+accountcode+String.valueOf(userId);
		return Long.parseLong(accNo);
	}

	public SAccount newAccount(String username, int userId) {
		SAccount account =new SAccount();
		account.setUserName(username);
		account.setAcctNo(generate_saving(userId));
		account.setUser(udao.findByUsername(username));
		return dao.save(account);
	}

}
