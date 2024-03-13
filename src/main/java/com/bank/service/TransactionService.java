package com.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dao.TransactionDao;
import com.bank.entity.Transaction;
import com.bank.exception.TransactionNotFoundException;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionDao transactionDao;
	
	public void delete(Long id) {
		this.transactionDao.findById(id)
			.orElseThrow(()-> new TransactionNotFoundException("Transaction Not Found with Id: " + id));
		transactionDao.deleteById(id);
	}
	
//	public List<Transaction> getAccountTransaction(long accId){
//		return this.transactionDao.findByAccountId(accId);
//	}
	
	public List<Transaction> getCustomerTransaction(long accId){
		return this.transactionDao.findByCustomerId(accId);
	}
	
	public Transaction getTransactionById(long tId) {
		return this.transactionDao.findById(tId)
				.orElseThrow(()-> new TransactionNotFoundException("Transaction Not Found with Id: " + tId));
	}

}
