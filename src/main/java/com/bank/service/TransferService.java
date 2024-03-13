package com.bank.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.dao.AccountDao;
import com.bank.dao.TransferDao;
import com.bank.entity.Account;
import com.bank.entity.Customer;
import com.bank.entity.Transfer;
import com.bank.exception.TransactionNotFoundException;

@Service
public class TransferService {
	
	@Autowired
	private TransferDao transferDao;
	@Autowired
	private AccountDao accountDao;
	
	
	
	public void transfer(Transfer transfer) throws AccountNotFoundException {
		//retrieve source and target accounts
		Long sourceAccountId = transfer.getSourceAccountId();
		Account sourceAccount = accountDao.findById(sourceAccountId)
				.orElseThrow(()-> new AccountNotFoundException("Account Not Found with Id: " + sourceAccountId));
		Long targetAccountId = transfer.getTargetAccountId();
		Account targetAccount = accountDao.findById(targetAccountId)
				.orElseThrow(()-> new AccountNotFoundException("Account Not Found with Id: " + sourceAccountId));
				
		BigDecimal transferAmount = transfer.getAmount();
		if(sourceAccount.getBalance().compareTo(transferAmount) < 0) {
			 ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance to transfer a amount");
		}
		 sourceAccount.setBalance(sourceAccount.getBalance().subtract(transferAmount));
		 targetAccount.setBalance(sourceAccount.getBalance().add(transferAmount));
		 
		 accountDao.save(sourceAccount);
		 accountDao.save(targetAccount);
		 
		 transfer.setAmount(transferAmount);
		 transfer.setSourceAccount(sourceAccount);
		 transfer.setTargetAccount(targetAccount);
		 transfer.setDateTime(LocalDateTime.now());
		 transferDao.save(transfer);
		
//		Account sourceAccount = accountDao.findById(transfer.getSourceAccountId());
//		Account targetAccount = accountDao.findById(transfer.getTargetAccountId());
//		
//		BigDecimal transferAmount = transfer.getAmount();
//		if(sourceAccount.getBalance().compareTo(transferAmount) < 0) {
//			 ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance to transfer a amount");
//		}
//		
//		//perform transfer money
//		sourceAccount.setBalance(sourceAccount.getBalance().subtract(transferAmount));
//		targetAccount.setBalance(targetAccount.getBalance().add(transferAmount));
//		
//		//save transfer entity
//		transfer.setAmount(transferAmount);
//		transfer.setSourceAccount(sourceAccount);
//		transfer.setTargetAccount(targetAccount);
//		transfer.setDateTime(LocalDateTime.now());
//		transferDao.save(transfer);
//		
	}
	
//	public void trans(Transfer transfer) {
//		Long sourceAccountId = transfer.getSourceAccountId();
//		Account sourceAccount = accountDao.findById(sourceAccountId);
//		Long targetAccountId = transfer.getTargetAccountId();
//		Account targetAccount = accountDao.findById(targetAccountId);
//		
//		BigDecimal transferAmount = transfer.getAmount();
//		if(sourceAccount.getBalance().compareTo(transferAmount) < 0) {
//			ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance to transfer a amount");
//		}
//		
//		sourceAccount.setBalance(sourceAccount.getBalance().subtract(transferAmount));
//		targetAccount.setBalance(targetAccount.getBalance().add(transferAmount));
//		
//		accountDao.save(sourceAccount);
//		accountDao.save(targetAccount);
//		
//		transfer.setAmount(transferAmount);
//		transfer.setDateTime(LocalDateTime.now());
//		
//		transfer.setSourceCustomer(sourceAccount.getCustomer());
//		transfer.setTargetCustomer(targetAccount.getCustomer());
//		
//		addTransferFrom(sourceAccount.getCustomer(),transfer);
//		addtransferTo(targetAccount.getCustomer(), transfer);
//		transferDao.save(transfer);
//	}
//	
//	public void addTransferFrom(Customer customer, Transfer transfer) {
//		customer.getTransferFrom().add(transfer);
//		transfer.setSourceCustomer(customer);
//	}
//	
//	public void addtransferTo(Customer customer, Transfer transfer) {
//		customer.getTransferTo().add(transfer);
//		transfer.setTargetCustomer(customer);
//	}
	
	public Transfer getTransferById(Long transferId) {
		return transferDao.findById(transferId)
				.orElseThrow(()-> new TransactionNotFoundException("Transaction Not Found with Id: " + transferId));
	}
	
	public List<Transfer> getAccountTransfer(Long cId){
		return transferDao.findByAccountId(cId);
	}
}
