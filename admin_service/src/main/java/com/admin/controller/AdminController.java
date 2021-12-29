package com.admin.controller;

import java.util.List;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.admin.model.ChequebookRequest;
import com.admin.model.User;
import com.admin.model.UserDisplay;
import com.admin.service.impl.AdminServiceImpl;
import com.admin.service.impl.MailServiceImpl;

@RestController
@CrossOrigin
public class AdminController {
	
	@Autowired
	private AdminServiceImpl service;
	
	@Autowired
	private MailServiceImpl mailService;
	
	@GetMapping("user/{userName}/features/{featureId}")
	public void setUserFeatures(@PathVariable("userName") String userName, @PathVariable("featureId") int featureId) {
		service.setUserFeatures(userName, featureId);
	}
	
	@GetMapping("user/{userName}/authorize")
	public void authorizeUser(@PathVariable("userName") String userName) {
		try {
			service.authorizeUser(userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("user/{userName}/authorize/cancel")
	public void  cancelAuthorization(@PathVariable("userName") String userName) {
		try {
			//Mail Service Call
		} catch (Exception e) {
			e.printStackTrace();
		}
		service.cancelAuthorization(userName);
	}
	
	@GetMapping("user/unAuthorized/all")
	public List<User> getAllUnauthorizedUsers()
	{
		return service.getAllUnauthorizedUsers();
	}
	
	@GetMapping("/user/all")
	public List<UserDisplay> getAllUsers()
	{
		return service.getAllUsers();
	}
	
	@GetMapping("/chequeBook/request/all")
	public List<ChequebookRequest> getAllChequeBookRequests()
	{
		return service.getAllChequebookRequests();
	}
	
	@GetMapping("/user/{acctNo}/chequeBook/request/confirm")
	public void confirmChequeBookRequest(@PathVariable("acctNo") long acctNo)
	{
		service.acceptChequebookRequest(acctNo);
	}
	
	@GetMapping("/user/{userName}/enable")
	public void enableUser(@PathVariable("userName") String userName)
	{
		service.enableUser(userName);
	}
	
	@GetMapping("/user/{userName}/disable")
	public void disableUser(@PathVariable("userName") String userName)
	{
		service.disableUser(userName);
	}
	
	@GetMapping("search/user/{userDetail}")
	public UserDisplay searchUser(@PathVariable("userDetail") String userDetail) {
		return service.searchUser(userDetail);
	}
	
}
