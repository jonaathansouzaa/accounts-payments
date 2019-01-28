package com.accountspayments.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accountspayments.model.AccountsPayment;
import com.accountspayments.repository.AccountsPaymentRepository;
import com.accountspayments.service.calculator.AccountsPaymentCalculator;

@Service
public class AccountsPaymentService {

	private AccountsPaymentRepository accountsPaymentRepository;
	private AccountsPaymentCalculator accountsPaymentCalculator;
	
	@Autowired
	public AccountsPaymentService(AccountsPaymentRepository accountsPaymentRepository, AccountsPaymentCalculator accountsPaymentCalculator) {
		this.accountsPaymentRepository = accountsPaymentRepository;
		this.accountsPaymentCalculator = accountsPaymentCalculator;
	}
	
	public AccountsPayment persist(AccountsPayment accounsPayment) {
		AccountsPayment accounsPaymentWithTaxes = accountsPaymentCalculator.calculateTaxes(accounsPayment);
		return accountsPaymentRepository.save(accounsPaymentWithTaxes);
	}

	public List<AccountsPayment> findAll() {
		return StreamSupport.stream(accountsPaymentRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

}
