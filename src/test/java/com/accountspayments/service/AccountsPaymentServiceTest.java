package com.accountspayments.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.accountspayments.api.builder.AccountsPaymentBuilder;
import com.accountspayments.model.AccountsPayment;
import com.accountspayments.repository.AccountsPaymentRepository;
import com.accountspayments.service.calculator.AccountsPaymentCalculator;

@RunWith(MockitoJUnitRunner.class)
public class AccountsPaymentServiceTest {

	@Mock
	private AccountsPaymentRepository accountsPaymentRepository;
	
	@Mock
	private AccountsPaymentCalculator accountsPaymentCalculator;
	
	@InjectMocks
	private AccountsPaymentService accountsPaymentService;
	
	private final String name = "Parking";
	private final Double originalValue = 200.00;
	private final LocalDate expirationDate = LocalDate.of(2018, 12, 01);
	private final LocalDate dateOfPayment = LocalDate.of(2018, 12, 05);
	private final Long daysOfDelay = 1L;
	private final Double finalValue = 204.2;

	@Test
	public void shouldReturnAnAccountsPaymentPersisted() {
		AccountsPayment accounsPayment = AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, expirationDate, dateOfPayment);
		
		AccountsPayment accounsPaymentWithTaxes = AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, daysOfDelay, finalValue, expirationDate, dateOfPayment);
		when(accountsPaymentCalculator.calculateTaxes(accounsPayment)).thenReturn(accounsPaymentWithTaxes);
		
		AccountsPayment expected = AccountsPaymentBuilder.buildAccountsPayment(1L, name, originalValue, daysOfDelay, finalValue, expirationDate, dateOfPayment);
		when(accountsPaymentRepository.save(accounsPaymentWithTaxes)).thenReturn(expected);
		
		assertThat(accountsPaymentService.persist(accounsPayment)).isEqualToComparingFieldByFieldRecursively(expected);
		
		verify(accountsPaymentCalculator).calculateTaxes(accounsPayment);
		verify(accountsPaymentRepository).save(accounsPaymentWithTaxes);
	}
	
	@Test
	public void shouldReturnAListOfAccountsPaymentWhenIsRequested() {
		List<AccountsPayment> expected = Arrays.asList(AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, daysOfDelay, finalValue, expirationDate, dateOfPayment));
		when(accountsPaymentRepository.findAll()).thenReturn(expected);
		
		List<AccountsPayment> actual = accountsPaymentService.findAll();
		assertEquals(expected.size(), actual.size());
		assertThat(expected.get(0)).isEqualToComparingFieldByFieldRecursively(actual.get(0));
	}
	
}
