package com.bank.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bank.dao.LoanDao;
import com.bank.entity.Customer;
import com.bank.entity.Loan;
import com.bank.exception.LoanNotFoundException;

@Service
public class LoanService {
	
	@Autowired
	private LoanDao loanDao;
	
	@Autowired
	private CustomerService customerService;
	
	
	public void applyForLoan(Long cId, Loan application) {
		Customer customerById = this.customerService.getCustomerById(cId);
		application.setCustomer(customerById);
		application.setApplicationDate(LocalDate.now());
		application.setApproved(false);
		application.setRejected(false);
		loanDao.save(application);
	}
	
	public List<Loan> getCustomerLoan(Long cId) {
		return this.loanDao.findByCustomerCId(cId);
	}
	
	public Loan approveLoan(Long loanId) {
		Loan approved = this.loanDao.findById(loanId)
				.orElseThrow(()-> new LoanNotFoundException("Loan not found with ID: " + loanId));
		approved.setApproved(true);
		approved.setRejected(false);
		this.loanDao.save(approved);
		return approved;
	}
	
	public void rejectLoan(Long loanId) {
		Loan rejected = this.loanDao.findById(loanId)
		.orElseThrow(()-> new LoanNotFoundException("Loan not found with ID: " + loanId));
		rejected.setRejected(true);
		rejected.setApproved(false);
		this.loanDao.save(rejected);
	}
	
	
	

}
