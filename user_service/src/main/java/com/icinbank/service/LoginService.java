package com.icinbank.service;

import com.icinbank.details.LoginDetails;
import com.icinbank.response.LoginResponse;

public interface LoginService {

	public LoginResponse custLogin(LoginDetails details);
}
