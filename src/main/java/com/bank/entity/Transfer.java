package com.bank.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transfer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long sourceAccountId;
	private Long targetAccountId;
	private BigDecimal amount;
	private LocalDateTime dateTime;
//	this is transfer to account relation
	
	@ManyToOne
	@JoinColumn(name = "source_account")
	private Account sourceAccount;
	@ManyToOne
	@JoinColumn(name = "target_account")
	private Account targetAccount;

	public Transfer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSourceAccountId() {
		return sourceAccountId;
	}

	public void setSourceAccountId(Long sourceAccountId) {
		this.sourceAccountId = sourceAccountId;
	}

	public Long getTargetAccountId() {
		return targetAccountId;
	}

	public void setTargetAccountId(Long targetAccountId) {
		this.targetAccountId = targetAccountId;
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
	
	public Account getSourceAccount() {
		return sourceAccount;
	}

	public void setSourceAccount(Account sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	public Account getTargetAccount() {
		return targetAccount;
	}

	public void setTargetAccount(Account targetAccount) {
		this.targetAccount = targetAccount;
	}

	

	

}
