package com.accountspayments.api.adapter;

import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.accountspayments.api.builder.AccountsPaymentBuilder;
import com.accountspayments.api.factory.AccountsPaymentFactory;
import com.accountspayments.model.AccountsPayment;
import com.accountspayments.request.AccountsPaymentRequest;
import com.accountspayments.response.AccountsPaymentResponse;
import com.accountspayments.service.AccountsPaymentService;

@RunWith(MockitoJUnitRunner.class)
public class AccountsPaymentAdapterTest {

	@Mock
	private AccountsPaymentFactory accountsPaymentFactory;
	
	@Mock
	private AccountsPaymentService accountsPaymentService;
	
	@InjectMocks
	private AccountsPaymentAdapter accountsPaymentAdapter;

	@Test
	public void shouldReturnTrueIfTheModelIsPersisterd() {
		AccountsPaymentRequest accountsPaymentRequest = AccountsPaymentBuilder.buildAccountsPaymentRequest("Supplier X", 250.50, LocalDate.of(2018, 1, 1), LocalDate.of(2018, 1, 1));
		
		AccountsPayment accounsPayment = null;
		when(accountsPaymentFactory.getInstance(accountsPaymentRequest)).thenReturn(accounsPayment);
		when(accountsPaymentService.persist(accounsPayment)).thenReturn(new AccountsPayment());
		
		Boolean isPersisted = accountsPaymentAdapter.handleRequest(accountsPaymentRequest);
		assertTrue(isPersisted);
		
		verify(accountsPaymentFactory).getInstance(accountsPaymentRequest);
		verify(accountsPaymentService).persist(accounsPayment);
	}
	
	@Test
	public void shouldReturnFalseIfTheModelIsNotPersisterd() {
		AccountsPaymentRequest accountsPaymentRequest = AccountsPaymentBuilder.buildAccountsPaymentRequest("Supplier X", 250.50, LocalDate.of(2018, 1, 1), LocalDate.of(2018, 1, 1));
		
		AccountsPayment accounsPayment = null;
		when(accountsPaymentFactory.getInstance(accountsPaymentRequest)).thenReturn(null);
		when(accountsPaymentService.persist(accounsPayment)).thenReturn(null);
		
		Boolean isPersisted = accountsPaymentAdapter.handleRequest(accountsPaymentRequest);
		assertFalse(isPersisted);
		
		verify(accountsPaymentFactory).getInstance(accountsPaymentRequest);
		verify(accountsPaymentService).persist(accounsPayment);
	}
	
	@Test
	public void shouldReturnAnExceptionIfSomeAttributeIsNull() {
		AccountsPaymentRequest accountsPaymentRequest = AccountsPaymentBuilder.buildAccountsPaymentRequest(null, 250.50, LocalDate.of(2018, 1, 1), LocalDate.of(2018, 1, 1));
		
		when(accountsPaymentFactory.getInstance(accountsPaymentRequest)).thenThrow(IllegalArgumentException.class);
		
		assertThatThrownBy(() -> accountsPaymentAdapter.handleRequest(accountsPaymentRequest))
	        .isInstanceOf(IllegalArgumentException.class);
	}
	
	@Test
	public void shouldReturnAListOfAccountsPaymentWhenRequested() {
		ArrayList<AccountsPayment> listOfAccountsPayments = new ArrayList<AccountsPayment>();
		
		when(accountsPaymentService.findAll()).thenReturn(listOfAccountsPayments);
		List<AccountsPaymentResponse> expected = new ArrayList<>();
		when(accountsPaymentFactory.getListOfInstance(listOfAccountsPayments)).thenReturn(expected);
		
		List<AccountsPaymentResponse> actual = accountsPaymentAdapter.handleRequest();
		assertEquals(expected, actual);
		
		verify(accountsPaymentService).findAll();
	}
}
