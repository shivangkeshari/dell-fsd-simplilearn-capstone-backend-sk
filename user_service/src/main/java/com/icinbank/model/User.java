package com.icinbank.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String fName;
	private String lName;
	private long phone;
	private String address;
	private String email;
	private String userName;
	private String password;
	 @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyy-MM-dd")
	private Date dob;
	private String identityType; 
	private String identity;
	
	@Column(columnDefinition = "boolean default false")
	private boolean status;
	
	@Column(columnDefinition = "boolean default false")
	private boolean authorizationStatus;
    @Column(columnDefinition = "integer default 3",nullable=false)
	private int featureStatus=3;

	public String getIdentityType() {
		return identityType;
	}
	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean getAuthorizationStatus() {
		return authorizationStatus;
	}
	public void setAuthorizationStatus(boolean authorizationStatus) {
		this.authorizationStatus = authorizationStatus;
	}
	public int getFeatureStatus() {
		return featureStatus;
	}
	public void setFeatureStatus(int featureStatus) {
		this.featureStatus = featureStatus;
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
	
	
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
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
