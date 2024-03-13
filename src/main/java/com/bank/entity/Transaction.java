package com.bank.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String transactionType; // e.g., deposit, withdrawal
	private BigDecimal amount;
	private LocalDateTime dateTime;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cus_id")
	private Customer customer;
	
//	@ManyToOne
//    @JoinColumn(name = "account_id")
//    private Account account;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(Long id, String transactionType, BigDecimal amount, LocalDateTime dateTime) {
		super();
		this.id = id;
		this.transactionType = transactionType;
		this.amount = amount;
		this.dateTime = dateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
	
	

}
