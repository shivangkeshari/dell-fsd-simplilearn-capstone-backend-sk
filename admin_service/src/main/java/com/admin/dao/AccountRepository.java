package com.admin.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.admin.model.Account;


@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>{

	public Account findByUserName(String userName);
	public Account findByAcctNo(long acctNo);
}
