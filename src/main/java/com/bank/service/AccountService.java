package com.bank.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dao.AccountDao;
import com.bank.dao.CustomerDao;
import com.bank.dao.TransactionDao;
import com.bank.entity.Account;
import com.bank.entity.Customer;
import com.bank.entity.Transaction;
import com.bank.exception.AccountNotFoundException;
import com.bank.exception.CustomerNotFoundException;

@Service
public class AccountService {
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private TransactionDao transactionDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	public Account addAccount(Account acc) {
		Account account = this.accountDao.save(acc);
		return account;
	}
	
//	public void deposit(Long accountId,BigDecimal amount) {
//		Account account = accountDao.findById(accountId);
//		int getcId = account.getCustomer().getcId();
//		account.setBalance(account.getBalance().add(amount));
//		this.accountDao.save(account);
//		Transaction transaction = new Transaction();
//		transaction.setAccount(account);
//		transaction.setAmount(amount);
//		transaction.setTransactionType("Deposit");
//		transaction.setDateTime(LocalDateTime.now());
//		this.transactionDao.save(transaction);
//		
//	}
	
	
	public void deposit(Long cId, BigDecimal amount) {
		Customer findById = customerDao.findById(cId)
				.orElseThrow(()-> new CustomerNotFoundException("Customer Not Found With Id: " + cId));
		List<Account> accounts = findById.getAccounts();
		for (Account account : accounts) {
			account.setBalance(account.getBalance().add(amount));
			accountDao.save(account);
		}
		Transaction transaction = new Transaction();
		transaction.setCustomer(findById);
		transaction.setAmount(amount);
		transaction.setTransactionType("Deposit");
		transaction.setDateTime(LocalDateTime.now());
		this.transactionDao.save(transaction);
	}
	
	public void withdraw(Long cId, BigDecimal amount) {
		Customer findById = customerDao.findById(cId)
				.orElseThrow(()-> new CustomerNotFoundException("Customer Not Found With Id: " + cId));
		List<Account> accounts = findById.getAccounts();
		for (Account account : accounts) {
			account.setBalance(account.getBalance().subtract(amount));
			this.accountDao.save(account);
		}
		Transaction trans = new Transaction();
		trans.setCustomer(findById);
		trans.setAmount(amount);
		trans.setTransactionType("Withdraw");
		trans.setDateTime(LocalDateTime.now());
		this.transactionDao.save(trans);
		//)
	}
	
	public List<Account> getAllAccount() {
		List<Account> findAll = accountDao.findAll();
		return findAll;
		
	}
	
	public Account getAccountById(Long accId) {
		Account account = accountDao.findById(accId)
			.orElseThrow(()-> new AccountNotFoundException("Account Not Found with id: " + accId));
		return account;
	}
	
//	public Customer getCustomerByAccountId(Long accId) {
//		Account findById = accountDao.findById(accId);
//		if(findById != null) {
//			return findById.getCustomer();
//		}
//		else {
//			return null;
//		}
//	}
	
	public List<Account> getCustomerAccount(Long customerId) {
		return this.accountDao.findByCustomerCId(customerId);
	}
	
	public Account update(long accId,String name) {
		Account accountById = this.getAccountById(accId);
		accountById.setName(name);
		accountDao.save(accountById);
		return accountById;
	}
	//Not implement properly
	public void closeAccount(Long accId) {
		Account findById = this.accountDao.findById(accId)
				.orElseThrow(null);
		this.accountDao.delete(findById);		
	}
	
//	public void withdraw(Long accountId, BigDecimal amount) {
//		Account account = accountDao.findById(accountId);
//		
//		if(account.getBalance().compareTo(amount) < 0) {
//			throw new BadCredentialsException("Insufficient funds for withdrawal");
//		}
//		
//		account.setBalance(account.getBalance().subtract(amount));
//		accountDao.save(account);
//		
//		Transaction transaction = new Transaction();
//		transaction.setAccount(account);
//		transaction.setAmount(amount);
//		transaction.setTransactionType("withdraw");
//		transaction.setDateTime(LocalDateTime.now());
//		transactionDao.save(transaction);
//	}
	
	
	
}
