package com.accountspayments.service.calculator;

import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import com.accountspayments.model.AccountsPayment;

@Component
public class AccountsPaymentCalculator {

	private static final Double BANK_FINE_TAX_UNTIL_THREE_DAYS = 2.0;
	private static final Double BANK_FINE_TAX_UNTIL_FIVE_DAYS = 3.0;
	private static final Double BANK_FINE_TAX_AFTER_FIVE_DAYS = 5.0;
	
	private static final Double BANK_INTEREST_TAX_UNTIL_THREE_DAYS = 0.1;
	private static final Double BANK_INTEREST_TAX_UNTIL_FIVE_DAYS = 0.2;
	private static final Double BANK_INTEREST_TAX_AFTER_FIVE_DAYS = 0.3;

	public AccountsPayment calculateTaxes(AccountsPayment accountsPayment) {
		accountsPayment.setDaysOfDelay(accountsPayment.getExpirationDate().until(accountsPayment.getDateOfPayment(), ChronoUnit.DAYS));
		accountsPayment = calculateBankFine(accountsPayment);
		accountsPayment = calculateBankInterest(accountsPayment);
		return accountsPayment;
	}

	AccountsPayment calculateBankFine(AccountsPayment accountsPayment) {
		accountsPayment.setFinalValue(defineFinalValue(accountsPayment.getOriginalValue(), accountsPayment.getOriginalValue(), accountsPayment.getDaysOfDelay(), BANK_FINE_TAX_UNTIL_THREE_DAYS, 
				BANK_FINE_TAX_UNTIL_FIVE_DAYS, BANK_FINE_TAX_AFTER_FIVE_DAYS));
		return accountsPayment;
	}

	AccountsPayment calculateBankInterest(AccountsPayment accountsPayment) {
		accountsPayment.setFinalValue(defineFinalValue(accountsPayment.getFinalValue(), accountsPayment.getOriginalValue(), accountsPayment.getDaysOfDelay(), BANK_INTEREST_TAX_UNTIL_THREE_DAYS, 
				BANK_INTEREST_TAX_UNTIL_FIVE_DAYS, BANK_INTEREST_TAX_AFTER_FIVE_DAYS));
		return accountsPayment;
	}
	
	private Double defineFinalValue(Double finalValue, Double originalValue, Long daysOfDelay, Double taxUntilThreeDays, Double taxUntilFiveDays, Double taxAfterFiveDays) {
		if (isGreaterThanZeroAndLessThanThree(daysOfDelay)) {
			return finalValue + ((originalValue * taxUntilThreeDays) / 100);
		} else if (isGreaterThanThreeAndLessThanFive(daysOfDelay)) {
			return finalValue + ((originalValue * taxUntilFiveDays) / 100);
		} else if (isGreaterThanFive(daysOfDelay)) {
			return finalValue + ((originalValue * taxAfterFiveDays) / 100);
		} else {
			return originalValue;
		}
	}

	private boolean isGreaterThanZeroAndLessThanThree(Long daysOfDelay) {
		return daysOfDelay > 0 && daysOfDelay <= 3;
	}
	
	private boolean isGreaterThanThreeAndLessThanFive(Long daysOfDelay) {
		return daysOfDelay > 3 && daysOfDelay <= 5;
	}
	
	private boolean isGreaterThanFive(Long daysOfDelay) {
		return daysOfDelay > 5;
	}

}
