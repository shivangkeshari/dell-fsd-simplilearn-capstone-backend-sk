package com.admin.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class ChequebookRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private long account;
	private String acctType;
	private boolean reqStatus;
	private LocalDate date;
	private int no_of_pages;
	
	public long getAccount() {
		return account;
	}
	public void setAccount(long account) {
		this.account = account;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getNo_of_pages() {
		return no_of_pages;
	}
	public void setNo_of_pages(int no_of_pages) {
		this.no_of_pages = no_of_pages;
	}
	public String getAcctType() {
		return acctType;
	}
	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}
	public boolean getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(boolean reqStatus) {
		this.reqStatus = reqStatus;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
