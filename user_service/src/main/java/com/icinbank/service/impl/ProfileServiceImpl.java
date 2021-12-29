package com.icinbank.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icinbank.dao.UserDisplayRepository;
import com.icinbank.dao.UserRepository;
import com.icinbank.details.UpdateDetails;
import com.icinbank.model.User;
import com.icinbank.model.UserDisplay;
import com.icinbank.response.UpdateResponse;
import com.icinbank.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService{

	@Autowired
	private UserRepository dao;
	
	@Autowired
	private UserDisplayRepository userDisplayDao;

	@Override
	public UpdateResponse updateUser(UpdateDetails user) {
		boolean flag=true;
		UpdateResponse response=new UpdateResponse();
		String message="Update successful"; 
		try {
			int counter = 0;
			User u=dao.findByUsername(user.getUserName());
			if(user.getAddress().length()!=0) {
				counter++;
				u.setAddress(user.getAddress());
			}
			if(user.getPassword().length()!=0 && user.getNewPassword().length()!=0) {
				counter++;
				String hashedPassword = DigestUtils.sha256Hex(user.getPassword());
				u.setPassword(hashedPassword);
			}
			if(user.getEmail().length()!=0) {
				counter++;
				u.setEmail(user.getEmail());
			}
			if(user.getPhone()!=0) {
				counter++;
				u.setPhone(user.getPhone());
			}
			System.out.println(counter);
			if(counter>0) {
				dao.save(u);
			}
			else {
				flag=false;
				message="Please enter some information to update";
			}
		}catch(Exception e){
			flag=false;
			response.setMsg("Update unsuccesful");
		}
		response.setMsg(message);
		response.setFlag(flag);
		return response;
	}

	@Override
	public User getUser(String userName) {
				return dao.findByUsername(userName);

	}

	@Override
	public UserDisplay userDisplay(String userName) {
		UserDisplay user=userDisplayDao.getCurrentUser(userName);
		return user;
	}
	}

