package com.accountspayments.service.calculator;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.accountspayments.api.builder.AccountsPaymentBuilder;
import com.accountspayments.model.AccountsPayment;

@RunWith(MockitoJUnitRunner.class)
public class AccountsPaymentCalculatorTest {

	@InjectMocks
	private AccountsPaymentCalculator accountsPaymentCalculator;

	private final String name = "Parking";
	private final Double originalValue = 200.00;
	private final LocalDate expirationDate = LocalDate.of(2018, 12, 01);
	
	@Test
	public void shouldReturnFiveDaysOfDelayIfTheDatesDifferenceIsFiveDays() {
		AccountsPayment expected = AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, 5L, 206.4, expirationDate, LocalDate.of(2018, 12, 06));
		AccountsPayment accountsPayment = AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, 5L, null, expirationDate, LocalDate.of(2018, 12, 06));
		
		AccountsPayment actual = accountsPaymentCalculator.calculateTaxes(accountsPayment);
		assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
	}
	
	@Test
	public void shouldReturnZeroDaysOfDelayIfTheDatesDifferenceIsZeroDays() {
		AccountsPayment expected = AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, 0L, 200.0, expirationDate, LocalDate.of(2018, 12, 01));
		AccountsPayment accountsPayment = AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, 0L, null, expirationDate, LocalDate.of(2018, 12, 01));
		
		AccountsPayment actual = accountsPaymentCalculator.calculateTaxes(accountsPayment);
		assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
	}
	
	@Test
	public void shouldReturnTheFinalValueWithBankFine_NoDelayDays() {
		AccountsPayment expected = AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, 0L, 200.0, expirationDate, LocalDate.of(2018, 12, 01));
		AccountsPayment accounsPayment = AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, 0L, null, expirationDate, LocalDate.of(2018, 12, 01));
		
		AccountsPayment actual = accountsPaymentCalculator.calculateBankFine(accounsPayment);
		assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
	}
	
	@Test
	public void shouldReturnTheFinalValueWithBankFine_untilThreeDaysOfDelay() {
		AccountsPayment expected = AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, 3L, 204.0, expirationDate, LocalDate.of(2018, 12, 04));
		AccountsPayment accounsPayment = AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, 3L, null, expirationDate, LocalDate.of(2018, 12, 04));
		
		AccountsPayment actual = accountsPaymentCalculator.calculateBankFine(accounsPayment);
		assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
	}
	
	@Test
	public void shouldReturnTheFinalValueWithBankFine_untilFiveDaysOfDelay() {
		AccountsPayment expected = AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, 5L, 206.0, expirationDate, LocalDate.of(2018, 12, 06));
		AccountsPayment accounsPayment = AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, 5L, null, expirationDate, LocalDate.of(2018, 12, 06));
		
		AccountsPayment actual = accountsPaymentCalculator.calculateBankFine(accounsPayment);
		assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
	}
	
	@Test
	public void shouldReturnTheFinalValueWithBankFine_moreThanFiveDelayDays() {
		AccountsPayment expected = AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, 6L, 210.0, expirationDate, LocalDate.of(2018, 12, 07));
		AccountsPayment accounsPayment = AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, 6L, null, expirationDate, LocalDate.of(2018, 12, 07));
		
		AccountsPayment actual = accountsPaymentCalculator.calculateBankFine(accounsPayment);
		assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
	}
	
	@Test
	public void shouldReturnTheFinalValueWithBankInterest_NoDelayDays() {
		AccountsPayment expected = AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, 0L, 200.0, expirationDate, LocalDate.of(2018, 12, 01));
		AccountsPayment accounsPayment = AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, 0L, 200.0, expirationDate, LocalDate.of(2018, 12, 01));
		
		AccountsPayment actual = accountsPaymentCalculator.calculateBankInterest(accounsPayment);
		assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
	}
	
	@Test
	public void shouldReturnTheFinalValueWithBankInterest_untilThreeDaysOfDelay() {
		AccountsPayment expected = AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, 3L, 200.2, expirationDate, LocalDate.of(2018, 12, 04));
		AccountsPayment accounsPayment = AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, 3L, 200.0, expirationDate, LocalDate.of(2018, 12, 04));
		
		AccountsPayment actual = accountsPaymentCalculator.calculateBankInterest(accounsPayment);
		assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
	}
	
	@Test
	public void shouldReturnTheFinalValueWithBankInterest_untilFiveDaysOfDelay() {
		AccountsPayment expected = AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, 5L, 200.4, expirationDate, LocalDate.of(2018, 12, 06));
		AccountsPayment accounsPayment = AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, 5L, 200.0, expirationDate, LocalDate.of(2018, 12, 06));
		
		AccountsPayment actual = accountsPaymentCalculator.calculateBankInterest(accounsPayment);
		assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
	}
	
	@Test
	public void shouldReturnTheFinalValueWithBankInterest_moreThanFiveDelayDays() {
		AccountsPayment expected = AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, 6L, 200.6, expirationDate, LocalDate.of(2018, 12, 07));
		AccountsPayment accounsPayment = AccountsPaymentBuilder.buildAccountsPayment(name, originalValue, 6L, 200.0, expirationDate, LocalDate.of(2018, 12, 07));
		
		AccountsPayment actual = accountsPaymentCalculator.calculateBankInterest(accounsPayment);
		assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
	}
	
}
