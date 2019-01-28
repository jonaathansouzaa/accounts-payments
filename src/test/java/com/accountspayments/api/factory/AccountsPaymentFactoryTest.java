package com.accountspayments.api.factory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.accountspayments.api.builder.AccountsPaymentBuilder;
import com.accountspayments.model.AccountsPayment;
import com.accountspayments.request.AccountsPaymentRequest;
import com.accountspayments.response.AccountsPaymentResponse;

@RunWith(MockitoJUnitRunner.class)
public class AccountsPaymentFactoryTest {

	private final String name = "Parking";
	private final Double originalValue = 150.00;
	private final LocalDate expirationDate = LocalDate.of(2018, 12, 01);
	private final LocalDate dateOfPayment = LocalDate.of(2018, 12, 05);
	private final Long daysOfDelay = 0L;
	private final Double finalValue = 150.00;

	@InjectMocks
	private AccountsPaymentFactory accountsPaymentFactory;
	
	@Test
	public void shouldReturnModelIfAllAttributesExists() {
		AccountsPaymentRequest accountsPaymentRequest = AccountsPaymentBuilder.buildAccountsPaymentRequest(name, originalValue, expirationDate, dateOfPayment);
		AccountsPayment expected = AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, expirationDate, dateOfPayment);
		AccountsPayment actual = accountsPaymentFactory.getInstance(accountsPaymentRequest);
		assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
	}
	
	@Test
	public void shouldReturnAnExceptionIfAccountsPaymentRequestNameIsNull() {
		AccountsPaymentRequest accountsPaymentRequest = AccountsPaymentBuilder.buildAccountsPaymentRequest(null, originalValue, expirationDate, dateOfPayment);
		assertThatThrownBy(() -> accountsPaymentFactory.getInstance(accountsPaymentRequest))
        	.isInstanceOf(IllegalArgumentException.class)
        	.hasMessage("Field name cannot be null.");
	}
	
	@Test
	public void shouldReturnAnExceptionIfAccountsPaymentRequestNameIsEmpty() {
		AccountsPaymentRequest accountsPaymentRequest = AccountsPaymentBuilder.buildAccountsPaymentRequest("", originalValue, expirationDate, dateOfPayment);
		assertThatThrownBy(() -> accountsPaymentFactory.getInstance(accountsPaymentRequest))
        	.isInstanceOf(IllegalArgumentException.class)
        	.hasMessage("Field name cannot be empty.");
	}
	
	@Test
	public void shouldReturnAnExceptionIfAccountsPaymentRequestiOriginalValueIsNull() {
		AccountsPaymentRequest accountsPaymentRequest = AccountsPaymentBuilder.buildAccountsPaymentRequest(name, null, expirationDate, dateOfPayment);
		assertThatThrownBy(() -> accountsPaymentFactory.getInstance(accountsPaymentRequest))
        	.isInstanceOf(IllegalArgumentException.class)
        	.hasMessage("Field original value cannot be null.");
	}
	
	@Test
	public void shouldReturnAnExceptionIfAccountsPaymentRequestExpirationDateIsNull() {
		AccountsPaymentRequest accountsPaymentRequest = AccountsPaymentBuilder.buildAccountsPaymentRequest(name, originalValue, null, dateOfPayment);
		assertThatThrownBy(() -> accountsPaymentFactory.getInstance(accountsPaymentRequest))
        	.isInstanceOf(IllegalArgumentException.class)
        	.hasMessage("Field expiration date cannot be null.");
	}
	
	@Test
	public void shouldReturnAnExceptionIfAccountsPaymentRequestDateOfPaymentIsNull() {
		AccountsPaymentRequest accountsPaymentRequest = AccountsPaymentBuilder.buildAccountsPaymentRequest(name, originalValue, expirationDate, null);
		assertThatThrownBy(() -> accountsPaymentFactory.getInstance(accountsPaymentRequest))
        	.isInstanceOf(IllegalArgumentException.class)
        	.hasMessage("Field day of payment cannot be null.");
	}
	
	@Test
	public void shouldReturnAResponseListWhenIReceiveAModelList() {
		AccountsPayment accountsPayment = AccountsPaymentBuilder.buildAccountsPayment(1L, name, originalValue, daysOfDelay, finalValue, expirationDate, dateOfPayment);
		AccountsPaymentResponse accountsPaymentResponse = AccountsPaymentBuilder.buildAccountsPaymentResponse(name, originalValue, daysOfDelay, finalValue, dateOfPayment);
		List<AccountsPayment> listOfAccountsPayments = Arrays.asList(accountsPayment);
		List<AccountsPaymentResponse> listOfInstance = accountsPaymentFactory.getListOfInstance(listOfAccountsPayments);
		assertEquals(Arrays.asList(accountsPaymentResponse).size(), listOfInstance.size());
		assertThat(listOfInstance.get(0)).isEqualToComparingFieldByFieldRecursively(accountsPaymentResponse);
	}
	
}
