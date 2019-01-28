package com.accountspayments.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts_payment")
public class AccountsPayment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accounts_payment_id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "original_value")
	private Double originalValue;
	
	@Column(name = "final_value")
	private Double finalValue;
	
	@Column(name = "days_of_delay")
	private Long daysOfDelay;
	
	@Column(name = "expiration_date")
	private LocalDate expirationDate;
	
	@Column(name = "date_of_payment")
	private LocalDate dateOfPayment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public LocalDate getDateOfPayment() {
		return dateOfPayment;
	}

	public void setDateOfPayment(LocalDate dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	@Override
	public String toString() {
		return "AccountsPayment [id=" + id + ", name=" + name + ", originalValue=" + originalValue + ", finalValue=" + finalValue + ", daysOfDelay=" + daysOfDelay + ", expirationDate=" + expirationDate + ", dateOfPayment=" + dateOfPayment + "]";
	}

}
