package com.bank.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Branch {
	@Id
	private Long branchCode;
    private String name;
    private String location;
    @OneToMany(mappedBy = "branch")
    private List<Account> accounts = new ArrayList<>();
    
	public Branch() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(Long branchCode) {
		this.branchCode = branchCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	
    
    

    
}
