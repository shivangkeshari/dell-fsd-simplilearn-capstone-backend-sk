package com.icinbank.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.icinbank.dao.AccountRepository;
import com.icinbank.dao.SAccountRepository;
import com.icinbank.details.TransactionDetails;
import com.icinbank.details.TransferDetails;
import com.icinbank.model.Account;
import com.icinbank.model.SAccount;
import com.icinbank.model.Transfer;
import com.icinbank.model.UserHistory;
import com.icinbank.response.DepositResponse;
import com.icinbank.response.TransferResponse;
import com.icinbank.response.WithdrawResponse;
import com.icinbank.service.AccountService;
import com.icinbank.service.SAccountService;
import com.icinbank.service.TransferHistoryService;
import com.icinbank.service.UserHistoryService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountController {
	
	@Autowired
	private AccountService service;
	
	@Autowired 
	private SAccountService sService;
	
	@Autowired
	private UserHistoryService hService;
	
	@Autowired
	private TransferHistoryService tService;
	
	@Autowired
	private AccountRepository aDao;
	
	@Autowired
	private SAccountRepository sDao;
	
	private final String ifsc="ICIN7465";
	
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
	
	@GetMapping("/account/details/{account}")
	public Account getAccountDetails(@PathVariable("account") int acct ) {
		
		return service.getAccountDetails(acct);
		
	}
	
	@PutMapping("/account/profile")
	public Account updateProfile(@RequestBody Account acct) {
		return service.updateAccount(acct);
	}
	
	@GetMapping("/account/getPrimary/{userName}")
	public Account getPrimaryDetails(@PathVariable("userName") String userName) {
		return service.getAccount(userName);
	}
	
	@GetMapping("/account/getSaving/{userName}")
	public SAccount getSavingDetails(@PathVariable("userName") String userName) {
		return sService.getAccount(userName);
	}
	
	@PostMapping("/account/deposit")
	public DepositResponse deposit(@RequestBody TransactionDetails details) {
		if(isprimary(details.getAcct())) {
			return service.depositAmt(details.getAcct(), details.getAmt());
		}
		else {
			return sService.deposit(details.getAcct(), details.getAmt());
		}
	}
	
	@PostMapping("/account/withdraw")
	public WithdrawResponse withdraw(@RequestBody TransactionDetails details) {
		
		if(isprimary(details.getAcct())) {
		return service.withdrawAmt(details.getAcct(), details.getAmt());
		}
		else {
			return sService.withdraw(details.getAcct(), details.getAmt());
		}
	}
	
	@PostMapping("/account/transfer")
	public TransferResponse transfer(@RequestBody TransferDetails details) {
		try {
			if(details.getIfsc().equals(ifsc)) 
			{
						Account p= aDao.findByUsername(details.getUserName());
						SAccount s= sDao.findByUserName(details.getUserName());
						
						if(p.getAcctNo()==details.getsAccount() || s.getAcctNo()==details.getsAccount()) {
						//String len = Integer.toString(details.getSaccount());
						if(isprimary(details.getsAccount())) {
						return service.transferAmt(details.getsAccount(), details.getrAccount(), details.getAmt());
						}
						else
						{
							return sService.transfer(details.getsAccount(), details.getrAccount(), details.getAmt());
						}
						}
						else {
							TransferResponse response=new TransferResponse();
							response.setsAccount(details.getsAccount());
							response.setResponseMessage("Dear user You can only transfer funds from the accounts registed with you");
							response.setTransferStatus(false);
							return response;
			}
			}
			else {
				TransferResponse response=new TransferResponse();
						response.setsAccount(details.getsAccount());
						response.setResponseMessage("IFSC code is incorrect");
						response.setTransferStatus(false);
						return response;
			}
		} catch (Exception e) {
			TransferResponse response=new TransferResponse();
			response.setsAccount(details.getsAccount());
			response.setResponseMessage("Please provide an IFSC code");
			response.setTransferStatus(false);
			return response;
			
		}
	}
	
	@GetMapping("/account/getHistory/{account}")
	public List<UserHistory> getHistory(@PathVariable("account") long acct )
	{
		List<UserHistory> history= hService.getHistory(acct);
		Collections.reverse(history);
		return history;
	}
	
	@GetMapping("/account/getTransfers/{account}")
	public List<Transfer> getTransfers(@PathVariable("account") long acct )
	{
		return tService.getTransfers(acct);
	}


}
