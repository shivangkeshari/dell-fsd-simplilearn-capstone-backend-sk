package com.icinbank.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.icinbank.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>{

	public Account findByUsername(String userName);
	public Account findByAcctNo(long acctNo);
}
