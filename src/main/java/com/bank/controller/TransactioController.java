package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.Customer;
import com.bank.entity.Transaction;
import com.bank.exception.TransactionNotFoundException;
import com.bank.service.TransactionService;

@RestController
public class TransactioController {
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id){
		try {
			transactionService.delete(id);
			return ResponseEntity.ok("Deleted Successfully");
		} catch (TransactionNotFoundException ex) {
			throw ex;
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
//	@GetMapping("account/{accId}/transactions")
//	public ResponseEntity<List<Transaction>> getAccountTransactions(@PathVariable("accId") long accId){
//		try {
//			List<Transaction> accountTransaction = this.transactionService.getAccountTransaction(accId);
//			return ResponseEntity.ok(accountTransaction);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//	}
	
	
	@GetMapping("customer/{cId}/transactions")
	public ResponseEntity<List<Transaction>> getCustomerTransactions(@PathVariable("cId") long cId){
		try {
			List<Transaction> customerTransactions = this.transactionService.getCustomerTransaction(cId);
			return ResponseEntity.ok(customerTransactions);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{tId}/getTranById")
	public ResponseEntity<Transaction> getTransactionById(@PathVariable("tId") long tId) {
		try {
			Transaction transactionById = this.transactionService.getTransactionById(tId);
			return ResponseEntity.ok(transactionById);
		} catch (TransactionNotFoundException ex) {
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
