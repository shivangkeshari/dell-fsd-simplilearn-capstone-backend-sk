package com.icinbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.icinbank.model.ChequebookRequest;
import com.icinbank.response.ChequeResponse;
import com.icinbank.service.ChequebookService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChequeBookController {
	@Autowired
	private ChequebookService service;

	@PostMapping("/cheque/request")
	public ChequeResponse createRequest(@RequestBody ChequebookRequest chequeBook) {
		return service.createRequest(chequeBook);
	}

	@GetMapping("/cheque/getByAccount/{account}")
	public List<ChequebookRequest> getRequests(@PathVariable("account") long acct) {
		List<ChequebookRequest> list=service.getRequests(acct);
		return list;
	}
}
