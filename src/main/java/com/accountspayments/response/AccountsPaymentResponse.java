package com.accountspayments.response;

import java.time.LocalDate;

public class AccountsPaymentResponse {

	private String name;
	private Double originalValue;
	private Double finalValue;
	private Long daysOfDelay;
	private LocalDate dateOfPayment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getOriginalValue() {
		return originalValue;
	}

	public void setOriginalValue(Double originalValue) {
		this.originalValue = originalValue;
	}

	public Double getFinalValue() {
		return finalValue;
	}

	public void setFinalValue(Double finalValue) {
		this.finalValue = finalValue;
	}

	public Long getDaysOfDelay() {
		return daysOfDelay;
	}

	public void setDaysOfDelay(Long daysOfDelay) {
		this.daysOfDelay = daysOfDelay;
	}

	public LocalDate getDateOfPayment() {
		return dateOfPayment;
	}

	public void setDateOfPayment(LocalDate dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

}
