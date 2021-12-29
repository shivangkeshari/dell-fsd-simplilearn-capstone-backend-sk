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
import com.icinbank.service.SAccountService;
import com.icinbank.service.TransferHistoryService;
import com.icinbank.service.UserHistoryService;
@Service
public class SAccountImpl implements SAccountService {
	
	@Autowired
	private SAccountRepository dao;
	
	@Autowired
	private UserHistoryService service;
	
	@Autowired
	private TransferHistoryService tservice;
	
	@Autowired
	private AccountRepository adao;
	
	@Autowired
	private UserRepository udao;
	
	private final String bankCode = "3914";
	private final String countryCode = "91";
	private final String branchCode = "820";
	private final String accountcode="2";

	@Override
	public SAccount getAccount(String userName) {
		return dao.findByUserName(userName);
	}
	
	@Override
	public SAccount getAccountDetails(long acct) {
		// TODO Auto-generated method stub
		return dao.findByAcctNo(acct);
	}
	
	public long generate_saving(int userId) {
		String acctNo = bankCode+countryCode+branchCode+accountcode+String.valueOf(userId);
		return Long.parseLong(acctNo);
	}

	@Override
	public SAccount newAccount(String userName, int userId) {
		SAccount account =new SAccount();
		account.setUserName(userName);
		account.setAcctNo(generate_saving(userId));
		account.setUser(udao.findByUsername(userName));
		return dao.save(account);
	}

	@Override
	public DepositResponse deposit(long acct, int amt) {
		DepositResponse response=new DepositResponse();
		boolean flag=true;
		try {
			SAccount sAcct=dao.findByAcctNo(acct);
			sAcct.setBalance(sAcct.getBalance()+ amt);
			service.addAction(acct, amt, sAcct.getBalance(), "deposit");
			dao.save(sAcct);
			response.setResponseMessage("Rs."+ amt +" successfully deposited into your account balance is now Rs."+sAcct.getBalance());
			response.setDepositStatus(flag);
		} catch (Exception e) {
			flag=false;
			response.setResponseMessage("Account number is incorrect");
			response.setDepositStatus(flag);
		}
		response.setAcct(acct);
		return response; 
	}

	@Override
	public WithdrawResponse withdraw(long acct, int amt) {
		WithdrawResponse response=new WithdrawResponse();
		boolean flag=true;
		
		try {
			SAccount sAcct=dao.findByAcctNo(acct);
			User user=udao.findByUsername(sAcct.getUserName());
			if(user.getFeatureStatus()==2 || user.getFeatureStatus()==3)
			{
			
			if(sAcct.getBalance()>= amt)
				{
				sAcct.setBalance(sAcct.getBalance()- amt);
				service.addAction(acct, amt, sAcct.getBalance(), "withdraw");
				dao.save(sAcct);
				response.setResponseMessage("Rs."+ amt +" successfully withdrawn your account balance is now Rs."+sAcct.getBalance());
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
		} catch (Exception e) {
			flag=false;
			response.setResponseMessage("Account number is incorrect");
			response.setWithdrawStatus(flag);
		}
		response.setAcct(acct);
		return response;
	}

	@Override
	public TransferResponse transfer(long sAccount, long rAccount, int amt) {
		TransferResponse response=new TransferResponse();
		boolean flag=true;
		
		try {
			SAccount sAcct=dao.findByAcctNo(sAccount);
			if(isprimary(rAccount)) {
				Account receiverAccount=adao.findByAcctNo(rAccount);
				if(sAcct.getAcctNo()!=receiverAccount.getAcctNo())
				{
				if(sAcct.getBalance()>= amt) {
					User user=udao.findByUsername(sAcct.getUserName());
					
					if(user.getFeatureStatus()==3) 
					{
						sAcct.setBalance(sAcct.getBalance()- amt);
						receiverAccount.setBalance(receiverAccount.getBalance()+ amt);
						tservice.addAction(sAccount, rAccount, amt);
						dao.save(sAcct);
						adao.save(receiverAccount);
						response.setResponseMessage("Rs."+ amt +" successfully transferred to account "+receiverAccount.getAcctNo());
						response.setTransferStatus(flag);
					}
					else {
						flag=false;
						response.setResponseMessage("This feature is not available for your account");
						response.setTransferStatus(flag);
					}
				}
				else 
					{
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
			else
			{
				SAccount receiverAccount=dao.findByAcctNo(rAccount);
				if(sAcct.getAcctNo()!=receiverAccount.getAcctNo())
				{
					
					if(sAcct.getBalance()> amt) {
						User user=udao.findByUsername(sAcct.getUserName());
						
						if(user.getFeatureStatus()==3) 
							{
								sAcct.setBalance(sAcct.getBalance()- amt);
								receiverAccount.setBalance(receiverAccount.getBalance()+ amt);
								tservice.addAction(sAccount, rAccount, amt);
								dao.save(sAcct);
								dao.save(receiverAccount);
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
		} catch (Exception e) {
			flag=false;
			response.setResponseMessage("Account number is incorrect");
			response.setTransferStatus(flag);
		}
		response.setsAccount(sAccount);
		return response;
	}
	
	public static boolean isprimary(long account) {
		String s = Long.toString(account).substring(0, 10);
		String check="3914918201";
		if(s.equals(check)) {
			return true;
		}
		else 
		{
			return false;
		}
		
	}

}
