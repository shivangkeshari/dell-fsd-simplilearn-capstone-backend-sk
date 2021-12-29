package com.icinbank.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icinbank.dao.AccountRepository;
import com.icinbank.dao.ChequeBookRepository;
import com.icinbank.dao.SAccountRepository;
import com.icinbank.model.Account;
import com.icinbank.model.ChequebookRequest;
import com.icinbank.model.SAccount;
import com.icinbank.response.ChequeResponse;
import com.icinbank.service.ChequebookService;

@Service
public class ChequebookServiceimpl implements ChequebookService{

	@Autowired
	private ChequeBookRepository dao;
	
	@Autowired 
	private AccountRepository aDao;
	
	@Autowired
	private SAccountRepository sDao;
	
	@Override
	public ChequeResponse createRequest(ChequebookRequest chequeBook) {
		ChequeResponse response=new ChequeResponse();
		long acct = chequeBook.getAcct();
		List<ChequebookRequest> prevRequests = dao.findByAccount(acct);
		if(!prevRequests.isEmpty()) {
			for(int i=0;i<prevRequests.size();i++) {
				if(prevRequests.get(i).isRequestStatus()==false) {
					response.setResponseMessage("Your previous chequebook request is still pending.");
					response.setStatus(false);
					response.setAcct(acct);
					return response;
				}
			}
		}
		LocalDate today = LocalDate.now();
		if(isprimary(acct)) {
			try {
				Account account1 = aDao.findByAcctNo(acct);
				response.setAcct(account1.getAcctNo());
				response.setStatus(true);
				response.setResponseMessage("Request submitted successfully");
				chequeBook.setAcctType("Primary");
				chequeBook.setDate(today);
				chequeBook.setRequestStatus(false);
				//dao.save(chequebook);
				dao.save(chequeBook);
				}
				catch(Exception e) {
					response.setAcct(acct);
					response.setStatus(false);
					response.setResponseMessage("account number is incorrect");
				}
		}
		else {
			if(isSecondary(acct)) {
				try {
					SAccount saccount= sDao.findByAcctNo(acct);
					response.setAcct(saccount.getAcctNo());
					response.setStatus(true);
					response.setResponseMessage("Request submitted successfully");
					chequeBook.setRequestStatus(false);
					chequeBook.setAcctType("Secondary");
					chequeBook.setDate(today);
					dao.save(chequeBook);
					} 
				catch (Exception e) {
					response.setAcct(acct);
					response.setStatus(false);
					response.setResponseMessage("account number is incorrect");
					}
		}
		else
		{
			response.setAcct(acct);
			response.setStatus(false);
			response.setResponseMessage("account number is incorrect");
		}
		
		}
		return response;
	}

	@Override
	public List<ChequebookRequest> getRequests(long acct) {
		return dao.findByAccount(acct);
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
	
	public static boolean isSecondary(long acct) {
		String s = Long.toString(acct).substring(0, 10);
		String check="3914918202";
		if(s.equals(check)) {
			return true;
		}
		else 
		{
			return false;
		}
		
	}
}
