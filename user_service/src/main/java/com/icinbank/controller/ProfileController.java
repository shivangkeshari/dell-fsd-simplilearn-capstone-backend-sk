package com.icinbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.icinbank.details.UpdateDetails;
import com.icinbank.model.User;
import com.icinbank.model.UserDisplay;
import com.icinbank.response.UpdateResponse;
import com.icinbank.service.ProfileService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfileController {

	@Autowired
	private ProfileService pService;
	
	@PutMapping("/profile/update")
	public UpdateResponse updateUser(@RequestBody UpdateDetails user) {
		return pService.updateUser(user);
	}

	@GetMapping("/profile/{userName}")
	public User getUser(@PathVariable("userName") String userName) {
		return pService.getUser(userName);
	}
	
	@GetMapping("home/{userName}")
	public UserDisplay userDisplay(@PathVariable("userName")String userName) {
		return pService.userDisplay(userName);
	}

}
