package com.icinbank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icinbank.model.SAccount;

@Repository
public interface SAccountRepository extends JpaRepository<SAccount, Integer>{

	public SAccount findByUserName(String userName);
	public SAccount findByAcctNo(long acctNo);
}
