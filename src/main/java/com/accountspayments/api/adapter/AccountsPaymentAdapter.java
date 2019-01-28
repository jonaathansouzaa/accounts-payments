package com.accountspayments.api.adapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accountspayments.api.factory.AccountsPaymentFactory;
import com.accountspayments.model.AccountsPayment;
import com.accountspayments.request.AccountsPaymentRequest;
import com.accountspayments.response.AccountsPaymentResponse;
import com.accountspayments.service.AccountsPaymentService;

@Component
public class AccountsPaymentAdapter {

	private AccountsPaymentFactory accountsPaymentFactory;
	private AccountsPaymentService accountsPaymentService;

	@Autowired
	public AccountsPaymentAdapter(AccountsPaymentFactory accountsPaymentFactory, AccountsPaymentService accountsPaymentService) {
		this.accountsPaymentFactory = accountsPaymentFactory;
		this.accountsPaymentService = accountsPaymentService;
	}
	
	public Boolean handleRequest(AccountsPaymentRequest accountsPaymentRequest) {
		AccountsPayment accountsPayment = accountsPaymentFactory.getInstance(accountsPaymentRequest);
		return accountsPaymentService.persist(accountsPayment) == null ? Boolean.FALSE : Boolean.TRUE;
	}

	public List<AccountsPaymentResponse> handleRequest() {
		List<AccountsPayment> listOfAccountsPayments = accountsPaymentService.findAll();
		return accountsPaymentFactory.getListOfInstance(listOfAccountsPayments);
	}

}
