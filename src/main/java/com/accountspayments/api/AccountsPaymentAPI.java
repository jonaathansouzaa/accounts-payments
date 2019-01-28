package com.accountspayments.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountspayments.api.adapter.AccountsPaymentAdapter;
import com.accountspayments.request.AccountsPaymentRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController()
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsPaymentAPI {

	private AccountsPaymentAdapter accountsPaymentAdapter;
	
	@Autowired
	public AccountsPaymentAPI(AccountsPaymentAdapter accountsPaymentAdapter) {
		this.accountsPaymentAdapter = accountsPaymentAdapter;
	}

	@PostMapping("/acccounts-payment")
	public ResponseEntity<?> insertAccountsPayment(@RequestBody AccountsPaymentRequest accountsPaymentDTO) {
		try {
			accountsPaymentAdapter.handleRequest(accountsPaymentDTO);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectMapper().createObjectNode().put("message", ex.getMessage()));
		}
		return ResponseEntity.ok(new ObjectMapper().createObjectNode().put("message", "Account payment insert successfully."));
	}
	
	@GetMapping("/acccounts-payment")
	public ResponseEntity<?> returnAListOfAccountsPayment() {
		return ResponseEntity.ok(accountsPaymentAdapter.handleRequest());
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<JsonNode> handleException(IllegalArgumentException ex) {
		return ResponseEntity.badRequest().body(new ObjectMapper().createObjectNode().put("message", ex.getMessage()));
	}

}
