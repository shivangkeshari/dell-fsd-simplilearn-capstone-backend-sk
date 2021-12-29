package com.icinbank.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icinbank.dao.TransferHistoryRepository;
import com.icinbank.model.Transfer;
import com.icinbank.service.TransferHistoryService;

@Service
public class TransferHistoryServiceImpl implements TransferHistoryService{

	@Autowired
	private TransferHistoryRepository dao;
	
	@Override
	public Transfer addAction(long sAccount, long rAccount, int amt) {
		LocalDate today = LocalDate.now();
		Transfer transfer=new Transfer();
		transfer.setsAccount(sAccount);
		transfer.setrAccount(rAccount);
		transfer.setAmount(amt);
		transfer.setDate(today);
		return dao.save(transfer);
	}

	@Override
	public List<Transfer> getTransfers(long acct) {
		List<Transfer> sender=dao.findBySAccount(acct);
		List<Transfer> receiver=dao.findByRAccount(acct);
		List<Transfer> merged=new ArrayList<>();
		merged.addAll(sender);
		merged.addAll(receiver);
		Collections.sort(merged);
		return merged;
	}

}
