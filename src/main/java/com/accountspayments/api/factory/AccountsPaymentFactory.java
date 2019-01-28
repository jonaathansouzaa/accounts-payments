package com.accountspayments.api.factory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.accountspayments.model.AccountsPayment;
import com.accountspayments.request.AccountsPaymentRequest;
import com.accountspayments.response.AccountsPaymentResponse;

@Component
public class AccountsPaymentFactory {

	public AccountsPaymentResponse getInstance(AccountsPayment accountsPayment) {
		AccountsPaymentResponse accountsPaymentResponse = new AccountsPaymentResponse();
		accountsPaymentResponse.setName(accountsPayment.getName());
		accountsPaymentResponse.setOriginalValue(accountsPayment.getOriginalValue());
		accountsPaymentResponse.setDaysOfDelay(accountsPayment.getDaysOfDelay());
		accountsPaymentResponse.setFinalValue(accountsPayment.getFinalValue());
		accountsPaymentResponse.setDateOfPayment(accountsPayment.getDateOfPayment());
		return accountsPaymentResponse;
	}
	
	public AccountsPayment getInstance(AccountsPaymentRequest accountsPaymentRequest) {
		executeValidationOfRequest(accountsPaymentRequest);
		AccountsPayment accountsPayment = new AccountsPayment();
		accountsPayment.setName(accountsPaymentRequest.getName());
		accountsPayment.setOriginalValue(accountsPaymentRequest.getOriginalValue());
		accountsPayment.setExpirationDate(accountsPaymentRequest.getExpirationDate());
		accountsPayment.setDateOfPayment(accountsPaymentRequest.getDateOfPayment());
		return accountsPayment;
	}

	private void executeValidationOfRequest(AccountsPaymentRequest accountsPaymentRequest) {
		if (accountsPaymentRequest.getName() == null) {
			throw new IllegalArgumentException("Field name cannot be null.");
		} else if (accountsPaymentRequest.getName().isEmpty()) {
			throw new IllegalArgumentException("Field name cannot be empty.");
		} else if (accountsPaymentRequest.getOriginalValue() == null) {
			throw new IllegalArgumentException("Field original value cannot be null.");
		} else if (accountsPaymentRequest.getExpirationDate() == null) {
			throw new IllegalArgumentException("Field expiration date cannot be null.");
		} else if (accountsPaymentRequest.getDateOfPayment() == null) {
			throw new IllegalArgumentException("Field day of payment cannot be null.");
		}
	}

	public List<AccountsPaymentResponse> getListOfInstance(List<AccountsPayment> listOfAccountsPayments) {
		return listOfAccountsPayments.stream().map(accountsPayment -> getInstance(accountsPayment)).collect(Collectors.toList());
	}

}
