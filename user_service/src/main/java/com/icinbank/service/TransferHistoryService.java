package com.icinbank.service;

import java.util.List;

import com.icinbank.model.Transfer;

public interface TransferHistoryService {

	public Transfer addAction(long sAccount, long rAccount, int amt);
	public List<Transfer> getTransfers(long acct);
}
