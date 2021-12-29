package com.icinbank.service;

import com.icinbank.model.Account;
import com.icinbank.response.DepositResponse;
import com.icinbank.response.TransferResponse;
import com.icinbank.response.WithdrawResponse;

public interface AccountService {

	public Account newAccount(String userName,int userId);
	public Account getAccount(String userName);
	public DepositResponse depositAmt(long acct, int amt);
	public WithdrawResponse withdrawAmt(long acct, int amt);
	public TransferResponse transferAmt(long sAccount, long rAccount, int amt);
	public Account getAccountDetails(long acct);
	public Account updateAccount(Account acct);
}
