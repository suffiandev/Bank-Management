package com.bank.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String accountNo;
	private String name;
	private BigDecimal balance;
	private Date dateOfCreation;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "customer_c_id")
	private Customer customer;
	
//	@JsonIgnore
//	@OneToMany(mappedBy = "account",orphanRemoval = true)
//	private List<Transaction> transactions;
//	
	// this is account to transfer relation
	@JsonIgnore
	@OneToMany(mappedBy = "sourceAccount",orphanRemoval = true)
	private List<Transfer> transferFrom;
	@JsonIgnore
	@OneToMany(mappedBy = "targetAccount",orphanRemoval = true)
	private List<Transfer> transferTo;
	
	@ManyToOne
	@JoinColumn(name = "branch_code")
	private Branch branch;
	
	public List<Transfer> getTransferFrom() {
		return transferFrom;
	}

	public void setTransferFrom(List<Transfer> transferFrom) {
		this.transferFrom = transferFrom;
	}

	public List<Transfer> getTransferTo() {
		return transferTo;
	}

	public void setTransferTo(List<Transfer> transferTo) {
		this.transferTo = transferTo;
	}

	//constructor
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//getters and setters
//	public List<Transfer> getTransferFrom() {
//		return transferFrom;
//	}
//
//	public void setTransferFrom(List<Transfer> transferFrom) {
//		this.transferFrom = transferFrom;
//	}
//
//	public List<Transfer> getTransferTo() {
//		return transferTo;
//	}
//
//	public void setTransferTo(List<Transfer> transferTo) {
//		this.transferTo = transferTo;
//	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

//	public List<Transaction> getTransactions() {
//		return transactions;
//	}
//
//	public void setTransactions(List<Transaction> transactions) {
//		this.transactions = transactions;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	
	
	

}
