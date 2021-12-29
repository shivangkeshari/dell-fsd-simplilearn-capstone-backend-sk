package com.admin.service.impl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.dao.AccountRepository;
import com.admin.dao.UserRepository;
import com.admin.model.Account;
import com.admin.model.User;

@Service
public class MailServiceImpl {
	
	@Autowired
	UserRepository userDAO;
	
	@Autowired
	AccountRepository  accountDAO;

}
