package com.icinbank.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class UserHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private long acct;
	private int amt;
	private String action;
	private LocalDate date;
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate today) {
		this.date = today;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public long getAcct() {
		return acct;
	}
	public void setAcct(long acct) {
		this.acct = acct;
	}
	public int getAmt() {
		return amt;
	}
	public void setAmt(int amt) {
		this.amt = amt;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
