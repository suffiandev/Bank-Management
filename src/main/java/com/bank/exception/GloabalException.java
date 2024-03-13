package com.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GloabalException {
	
	public GloabalException() {
        System.out.println("GlobalException instantiated.");
    }
	
	@ExceptionHandler(LoanNotFoundException.class)
	public ResponseEntity<String> handleLoanNotFoundException(LoanNotFoundException ex){
		String errorMessage = ex.getMessage();
        String formattedMessage = "{\"message\": \"" + errorMessage + "\"}";
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(formattedMessage);
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException ex){
		String message = ex.getMessage();
		String convert = "{\"message\": \"" + message + "\"}";
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(convert);
		
	}
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<String> handleAccountNotFoundException(AccountNotFoundException ex){
		String message = ex.getMessage();
		String convert = "{\"message\": \"" + message + "\"}";
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(convert);
	}
	
	@ExceptionHandler(TransactionNotFoundException.class)
	public ResponseEntity<String> handleTransactionNotFoundException(TransactionNotFoundException ex){
		String message = ex.getMessage();
		String convert = "{\"message\": \"" + message + "\"}";
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(convert);
	}
	
	@ExceptionHandler(TransferNotFoundException.class)
	public ResponseEntity<String> handleTransferNotFoundException(TransferNotFoundException ex){
		String message = ex.getMessage();
		String convert = "{\"message\": \"" + message + "\"}";
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(convert);
	}
	
	@ExceptionHandler(BranchNotFoundException.class)
	public ResponseEntity<String> handleBranchNotFoundException(BranchNotFoundException ex){
		String message = ex.getMessage();
		String convert = "{\"message\": \"" + message + "\"}";
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(convert);
	}
}
