package com.icinbank.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Transfer implements Comparable<Transfer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private long sAccount;
	private long rAccount;
	private int amount;
	private LocalDate date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public long getsAccount() {
		return sAccount;
	}
	public void setsAccount(long sAccount) {
		this.sAccount = sAccount;
	}
	public long getrAccount() {
		return rAccount;
	}
	public void setrAccount(long rAccount) {
		this.rAccount = rAccount;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	@Override
	public int compareTo(Transfer o) {
		Integer val1=this.id;
		Integer val2=o.id;
		return val2.compareTo(val1);
	}
	
	
}
