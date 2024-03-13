package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.Loan;
import com.bank.exception.LoanNotFoundException;
import com.bank.service.LoanService;

@RestController
public class LoanController {
	
	@Autowired
	private LoanService loanService;
	
	@PostMapping("/{cId}/applyForLoan")
	public ResponseEntity<Void> applyForLoan(@PathVariable("cId") Long cId,Loan loan){
		try {
			this.loanService.applyForLoan(cId,loan);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/customer/{cId}/loans")
	public ResponseEntity<List<Loan>> getCustomerLoans(@PathVariable("cId") Long cId){
		try {
			List<Loan> customerLoan = this.loanService.getCustomerLoan(cId);
			return ResponseEntity.ok(customerLoan);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/{loanId}/approveLoan")
    public ResponseEntity<Loan> approveLoan(@PathVariable("loanId") Long loanId) {
        try {
            Loan approvedLoan = loanService.approveLoan(loanId);
            return ResponseEntity.ok(approvedLoan);
        } catch (LoanNotFoundException ex) {
        	throw ex;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	
	@PutMapping("/{loanId}/rejectLoan")
	public ResponseEntity<Void> rejectLoan(@PathVariable("loanId") Long loanId){
		try {
			this.loanService.approveLoan(loanId);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (LoanNotFoundException ex) {
        	throw ex;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	

}
