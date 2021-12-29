package com.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.model.SAccount;

@Repository
public interface SaccountRepository extends JpaRepository<SAccount, Integer>{
	
	public SAccount findByAccno(long accNo);

}