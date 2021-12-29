package com.icinbank.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.icinbank.dao.UserRepository;
import com.icinbank.model.User;
import com.icinbank.response.RegisterResponse;
import com.icinbank.service.AccountService;
import com.icinbank.service.RegistrationService;
import com.icinbank.service.SAccountService;

@Service
public class RegistrationServiceImpl implements RegistrationService{
	
	@Autowired
	private UserRepository dao;
	
	@Autowired
	private AccountService service;
	
	@Autowired 
	private SAccountService sService;
	
	@Override
	public RegisterResponse createAccount(User user){
		
		RegisterResponse response = new RegisterResponse();
		boolean flag = true;
		String message = "Registration Succesful";
		
		if(emailAlreadyExists(user.getEmail())) {
			message = "Email already Exists";
			flag = false;
		}
		
		if(phoneAlreadyExists(user.getPhone())) {
			message = "Phone number already Exists";
			flag = false;
		}
		
		if(userNameAlreadyExists(user.getUserName())) {
			message = "Username already Exists";
			flag = false;
		}
		
		if(flag) {
			String hashedPassword = DigestUtils.sha256Hex(user.getPassword());
			user.setPassword(hashedPassword);
			dao.save(user);
//			User u=dao.findByUsername(user.getUsername());
//			int id=u.getId();
//			service.newAccount(user.getUsername(),id);
//			sservice.newAccount(user.getUsername(),id);
		}
		response.setRegistrationStatus(flag);
		response.setResponseMessage(message);
		response.setUserName(user.getUserName());
		return response;
		
	}
	
	@Override
	public boolean userNameAlreadyExists(String userName) {
		try {
			User u=dao.findByUsername(userName);
			System.out.println(u.toString());
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	
	@Override
	public boolean emailAlreadyExists(String email) {
		try {
			User u=dao.findByEmail(email);
			System.out.println(u.toString());
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	
	@Override
	public boolean phoneAlreadyExists(long l) {
		try {
			User u=dao.findByPhone(l);
			System.out.println(u.toString());
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	

}