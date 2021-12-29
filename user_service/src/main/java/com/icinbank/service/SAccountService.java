package com.icinbank.service;

import com.icinbank.model.SAccount;
import com.icinbank.response.DepositResponse;
import com.icinbank.response.TransferResponse;
import com.icinbank.response.WithdrawResponse;

public interface SAccountService {
	public SAccount getAccountDetails(long acct);
	public SAccount getAccount(String userName);
	public SAccount newAccount(String userName, int userId);
	public DepositResponse deposit(long acct,int amt);
	public WithdrawResponse withdraw(long acct,int amt);
	public TransferResponse transfer(long sAccount,long rAccount,int amt);
}

