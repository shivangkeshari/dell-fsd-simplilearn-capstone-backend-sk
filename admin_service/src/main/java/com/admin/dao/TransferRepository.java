package com.admin.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.admin.model.Transfer;

@Repository
public interface TransferRepository extends CrudRepository<Transfer, Integer> {
	
	public List<Transfer> findBySAccount(long sAccount);
	public List<Transfer> findByRAccount(long rAccount);
}
