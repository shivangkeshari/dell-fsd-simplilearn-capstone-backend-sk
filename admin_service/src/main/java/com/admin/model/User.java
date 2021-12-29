package com.admin.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="user")
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	private String fName;
	private String lName;
	private long  phone;
	private String address;
	private String email;
	private String userName;
	private String password;
	 @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date dob;
	private String identityType; 
	private String identity;
	
	@Column(columnDefinition = "boolean default false")
	private boolean status;
	@Column(columnDefinition = "boolean default false")
	private boolean authorizationStatus;
    @Column(columnDefinition = "integer default 3",nullable=false)
	private int featureStatus=3;
    
    @OneToOne(targetEntity = Account.class,mappedBy = "user",orphanRemoval = false, fetch = FetchType.LAZY)
	private Account account;
	
	@OneToOne(targetEntity = SAccount.class,mappedBy = "user",orphanRemoval = false, fetch = FetchType.LAZY)
	private SAccount sAccount;
	
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getIdentityType() {
		return identityType;
	}
	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public int getFeatureStatus() {
		return featureStatus;
	}
	public void setFeatureStatus(int featureStatus) {
		this.featureStatus = featureStatus;
	}
	public boolean isStatus() {
		return status;
	}
	public boolean isAuthorizationStatus() {
		return authorizationStatus;
	}
	public void setAuthorizationStatus(boolean authorizationStatus) {
		this.authorizationStatus = authorizationStatus;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
