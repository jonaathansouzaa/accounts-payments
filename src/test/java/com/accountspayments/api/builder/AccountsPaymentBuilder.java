package com.accountspayments.api.builder;

import java.time.LocalDate;

import com.accountspayments.model.AccountsPayment;
import com.accountspayments.request.AccountsPaymentRequest;
import com.accountspayments.response.AccountsPaymentResponse;

public class AccountsPaymentBuilder {

	public static AccountsPaymentRequest buildAccountsPaymentRequest(String name, Double originalValue, LocalDate expirationDate, LocalDate dateOfPayment) {
		AccountsPaymentRequest accountsPaymentRequest = new AccountsPaymentRequest();
		accountsPaymentRequest.setName(name);
		accountsPaymentRequest.setOriginalValue(originalValue);
		accountsPaymentRequest.setExpirationDate(expirationDate);
		accountsPaymentRequest.setDateOfPayment(dateOfPayment);
		return accountsPaymentRequest;
	}

	public static AccountsPayment buildAccountsPayment(String name, Double originalValue, LocalDate expirationDate, LocalDate dateOfPayment) {
		AccountsPayment accountsPayment = new AccountsPayment();
		accountsPayment.setName(name);
		accountsPayment.setOriginalValue(originalValue);
		accountsPayment.setExpirationDate(expirationDate);
		accountsPayment.setDateOfPayment(dateOfPayment);
		return accountsPayment;
	}
	
	public static AccountsPayment buildAccountsPayment(String name, Double originalValue, Long daysOfDelay, Double finalValue, LocalDate expirationDate, LocalDate dateOfPayment) {
		AccountsPayment accountsPayment = new AccountsPayment();
		accountsPayment.setName(name);
		accountsPayment.setOriginalValue(originalValue);
		accountsPayment.setDaysOfDelay(daysOfDelay);
		accountsPayment.setFinalValue(finalValue);
		accountsPayment.setExpirationDate(expirationDate);
		accountsPayment.setDateOfPayment(dateOfPayment);
		return accountsPayment;
	}

	public static AccountsPayment buildAccountsPayment(Long id, String name, Double originalValue, Long daysOfDelay, Double finalValue, LocalDate expirationDate, LocalDate dateOfPayment) {
		AccountsPayment accountsPayment = new AccountsPayment();
		accountsPayment.setId(id);
		accountsPayment.setName(name);
		accountsPayment.setOriginalValue(originalValue);
		accountsPayment.setDaysOfDelay(daysOfDelay);
		accountsPayment.setFinalValue(finalValue);
		accountsPayment.setExpirationDate(expirationDate);
		accountsPayment.setDateOfPayment(dateOfPayment);
		return accountsPayment;
	}
	
	public static AccountsPaymentResponse buildAccountsPaymentResponse(String name, Double originalValue, Long daysOfDelay, Double finalValue, LocalDate dateOfPayment) {
		AccountsPaymentResponse accountsPayment = new AccountsPaymentResponse();
		accountsPayment.setName(name);
		accountsPayment.setOriginalValue(originalValue);
		accountsPayment.setDaysOfDelay(daysOfDelay);
		accountsPayment.setFinalValue(finalValue);
		accountsPayment.setDateOfPayment(dateOfPayment);
		return accountsPayment;
	}

}
