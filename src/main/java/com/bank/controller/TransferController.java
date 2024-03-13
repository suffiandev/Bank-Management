package com.bank.controller;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.Transfer;
import com.bank.exception.TransactionNotFoundException;
import com.bank.service.TransferService;

@RestController
public class TransferController {
	@Autowired
	private TransferService transferService;
	
	@PostMapping("/transfer")
	public ResponseEntity<String> transfer(@RequestBody Transfer transfer) throws AccountNotFoundException {
		try {
			transferService.transfer(transfer);
			return ResponseEntity.ok("Money Transfer successfully");
		} catch (AccountNotFoundException ex) {
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{id}/getTransferById")
	public ResponseEntity<Transfer> getTransferById(@PathVariable("id") long transferId){
		try {
			Transfer transferById = transferService.getTransferById(transferId);
			return ResponseEntity.ok(transferById);
		} catch (TransactionNotFoundException ex) {
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("account/{id}/transfers")
	public ResponseEntity<List<Transfer>> getAccountTransfers(@PathVariable("id") Long transferId){ 
		try {
			List<Transfer> accountTransfer = transferService.getAccountTransfer(transferId);
			return ResponseEntity.ok(accountTransfer);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	

}
