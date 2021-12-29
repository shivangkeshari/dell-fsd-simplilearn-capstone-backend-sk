 package com.icinbank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icinbank.dao.AccountRepository;
import com.icinbank.dao.SAccountRepository;
import com.icinbank.dao.UserRepository;
import com.icinbank.model.Account;
import com.icinbank.model.SAccount;
import com.icinbank.model.User;
import com.icinbank.response.DepositResponse;
import com.icinbank.response.TransferResponse;
import com.icinbank.response.WithdrawResponse;
import com.icinbank.service.AccountService;
import com.icinbank.service.TransferHistoryService;
import com.icinbank.service.UserHistoryService;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepository dao;
	
	@Autowired
	private UserHistoryService service;
	
	@Autowired
	private TransferHistoryService tService;
	
	@Autowired
	private UserRepository uDao;
	
	@Autowired
	private SAccountRepository sDao;
	
	private final String bankCode = "3914";
	private final String countryCode = "91";
	private final String branchCode = "820";
	private final String accountcode="1";

	public long generate_saving(int userId) {
		String acctNo = bankCode+countryCode+branchCode+accountcode+String.valueOf(userId);
		return Long.parseLong(acctNo);
	}
	
	public static boolean isprimary(long acct) {
		String s = Long.toString(acct).substring(0, 10);
		String check="3914918201";
		if(s.equals(check)) {
			return true;
		}
		else 
		{
			return false;
		}
		
	}

	@Override
	public Account newAccount(String userName, int userId) {
		Account acct=new Account();
		acct.setUserName(userName);
		acct.setAcctNo(generate_saving(userId));
		acct.setUser(uDao.findByUsername(userName));
		return dao.save(acct);

	}

	@Override
	public Account getAccount(String userName) {
		return dao.findByUsername(userName);
	}

	@Override
	public DepositResponse depositAmt(long acct, int amt) {
		DepositResponse response=new DepositResponse();
		
		boolean flag=true;
		try {
			Account account=dao.findByAcctNo(acct);
			account.setBalance(account.getBalance()+ amt);
			service.addAction(acct, amt, account.getBalance(), "credit");
			dao.save(account);
			response.setResponseMessage("Rs."+ amt +" successfully deposited into your account balance is now Rs."+account.getBalance());
			response.setDepositStatus(flag);
		} 
		catch (Exception e) {
			flag=false;
			response.setResponseMessage("Account number is incorrect");
			response.setDepositStatus(flag);
		}
		response.setAcct(acct);
		return response; 
	}

	@Override
	public WithdrawResponse withdrawAmt(long acct, int amt) {
		WithdrawResponse response=new WithdrawResponse();
		boolean flag=true;
		try {
			Account account=dao.findByAcctNo(acct);
			User user= uDao.findByUsername(account.getUserName());
			if(user.getFeatureStatus()==2 || user.getFeatureStatus()==3)
			{
			if(account.getBalance()>= amt)
				{
					account.setBalance(account.getBalance()- amt);
					service.addAction(acct, amt, account.getBalance(), "debit");
					dao.save(account);
					response.setResponseMessage("Rs."+ amt +" successfully withdrawn your account balance is now Rs."+account.getBalance());
					response.setWithdrawStatus(flag);
				}
			else 
				{
					flag=false;
					response.setResponseMessage("Insufficient funds to complete the transaction");
					response.setWithdrawStatus(flag);
				}
			}
			else {
				flag=false;
				response.setResponseMessage("This function is not available for your account");
				response.setWithdrawStatus(flag);
			}
			
		} 
		
		catch (Exception e) {
			flag=false;
			response.setResponseMessage("Account number is incorrect");
			response.setWithdrawStatus(flag);
		}
		
		response.setAcct(acct);
		return response;
	}

	@Override
	public TransferResponse transferAmt(long sAccount, long rAccount, int amt) {
		TransferResponse response=new TransferResponse();
		boolean flag=true;
		
		try {
			Account senderAccount=dao.findByAcctNo(sAccount);
			//String check = Long.toString(raccount);
			if(isprimary(rAccount))
			{
				Account receiverAccount=dao.findByAcctNo(rAccount);
				if(senderAccount.getAcctNo()!=receiverAccount.getAcctNo())
				{
					if(senderAccount.getBalance()> amt) {
						User user= uDao.findByUsername(senderAccount.getUserName());
						
						if(user.getFeatureStatus()==3) 
						{
						senderAccount.setBalance(senderAccount.getBalance()- amt);
						receiverAccount.setBalance(receiverAccount.getBalance()+ amt);
						tService.addAction(sAccount, rAccount, amt);
						dao.save(senderAccount);
						dao.save(receiverAccount);
						response.setResponseMessage("Rs."+ amt +" successfully transferred to account "+receiverAccount.getAcctNo());
						response.setTransferStatus(flag);
						}
						else {
							flag=false;
							response.setResponseMessage("This feature is not available for your account");
							response.setTransferStatus(flag);
						}
					}
					else {
						flag=false;
						response.setResponseMessage("Insufficient funds to complete the transfer");
						response.setTransferStatus(flag);
						}
				}
				else {
					flag=false;
					response.setResponseMessage("sender and recieiver accounts are same");
					response.setTransferStatus(flag);
				}
			}
			else {
				SAccount receiverAccount= sDao.findByAcctNo(rAccount);
				if(senderAccount.getAcctNo()!=receiverAccount.getAcctNo())
				{
					if(senderAccount.getBalance()> amt) {
						
						User user= uDao.findByUsername(senderAccount.getUserName());
						
						if(user.getFeatureStatus()==3) 
							{
						senderAccount.setBalance(senderAccount.getBalance()- amt);
						receiverAccount.setBalance(receiverAccount.getBalance()+ amt);
						tService.addAction(sAccount, rAccount, amt);
						dao.save(senderAccount);
						sDao.save(receiverAccount);
						response.setResponseMessage("Rs."+ amt +" successfully transferred to account "+receiverAccount.getAcctNo());
						response.setTransferStatus(flag);
							}
						else {
							flag=false;
							response.setResponseMessage("This function isnt available for the account");
							response.setTransferStatus(flag);
						}
						}
					else {
						flag=false;
						response.setResponseMessage("Insufficient funds to complete the transfer");
						response.setTransferStatus(flag);
						}
				}
				else {
					flag=false;
					response.setResponseMessage("sender and recieiver accounts are same");
					response.setTransferStatus(flag);
				}
			}
		} 
		
		catch (Exception e) {
			flag=false;
			response.setResponseMessage("Account number is incorrect");
			response.setTransferStatus(flag);
		}
		response.setsAccount(sAccount);
		return response;
	}

	@Override
	public Account getAccountDetails(long acct) {
		return dao.findByAcctNo(acct);
	}

	@Override
	public Account updateAccount(Account acct) {
		return dao.save(acct);
	}

}
