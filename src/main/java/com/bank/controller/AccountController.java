package com.bank.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.Account;
import com.bank.entity.Branch;
import com.bank.entity.Customer;
import com.bank.entity.Transaction;
import com.bank.exception.AccountNotFoundException;
import com.bank.exception.CustomerNotFoundException;
import com.bank.service.AccountService;
import com.bank.service.BranchService;
import com.bank.service.CustomerService;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BranchService branchService;
	
	
	@PostMapping("/addAccount")
	public ResponseEntity<Account> addAcc(@RequestBody Account account,@RequestParam("cId") Long cId, @RequestParam("bCode") Long bCode){
		try {
			Customer customer = customerService.getCustomerById(cId);
			Branch branchByBranchCode = branchService.getBranchByBranchCode(bCode);
			
			if (customer == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			else if (branchByBranchCode == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			account.setCustomer(customer);
			account.setBranch(branchByBranchCode);
			Account addAccount = this.accountService.addAccount(account);
			addAccount.setCustomer(null);
			addAccount.setBranch(null);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(addAccount);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

//	@PostMapping("/{accountId}/deposit")
//	public ResponseEntity<String> deposit(@PathVariable Long accountId, @RequestBody Transaction trans) {
//		try {
//			BigDecimal amount = trans.getAmount();
//			if (amount.compareTo(BigDecimal.ZERO) <= 0) {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid deposit amount");
//			}
//
//			this.accountService.deposit(accountId, amount);
//			return ResponseEntity.ok("Successfully deposit");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//	}
	
	@PostMapping("/{customerId}/deposit")
	public ResponseEntity<String> deposit(@PathVariable Long customerId, @RequestBody Transaction trans) {
		try {
			BigDecimal amount = trans.getAmount();
			if (amount.compareTo(BigDecimal.ZERO) <= 0) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid deposit amount");
			}

			this.accountService.deposit(customerId, amount);
			return ResponseEntity.ok("Successfully deposit");
		} catch (CustomerNotFoundException ex) {
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

//	@PostMapping("/{accountId}/withdraw")
//	public ResponseEntity<String> withdraw(@PathVariable Long accountId, @RequestBody Map<String, BigDecimal> requestBody) {
//	    try {
//	        BigDecimal amount = requestBody.get("amount");
//
//	        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
//	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid withdraw amount");
//	        }
//
//	        accountService.with(accountId, amount);
//	        return ResponseEntity.status(HttpStatus.OK).body("Withdrawal Successfully done.");
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//	    }
//	}
	
	@PostMapping("/{cId}/withdraw")
	public ResponseEntity<String> withdraw(@PathVariable("cId") Long cId, @RequestBody Transaction trans ){
		try {
			BigDecimal amount = trans.getAmount();
			if(amount==null || amount.compareTo(BigDecimal.ZERO) <=0 ) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid WithDraw Amount");
			}
			this.accountService.withdraw(cId, amount);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Withdraw Successfully done");
		} catch (CustomerNotFoundException ex) {
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/getAllAccount")
	public ResponseEntity<List<Account>> getAllAccount(){
		try {
			List<Account> allAccount = this.accountService.getAllAccount();
			if(allAccount == null || allAccount.isEmpty()) {
				return ResponseEntity.ok(allAccount);
			}
			return ResponseEntity.ok(allAccount);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{accId}/getAccountById")
	public ResponseEntity<Account> getAccountById(@PathVariable("accId") long accId){
		try {
			Account accountById = accountService.getAccountById(accId);
			return ResponseEntity.ok(accountById);
		} catch (AccountNotFoundException ex) {
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/customer/{cId}/accounts")
	public ResponseEntity<List<Account>> getCustomerAccount(@PathVariable("cId") Long cId){
		try {
			List<Account> customerAccount = this.accountService.getCustomerAccount(cId);
			return ResponseEntity.ok(customerAccount);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/{accId}/updateAccount")
	public ResponseEntity<Account> updateAccount(@PathVariable("accId") long accId, @RequestBody Account acc){
		try {
			String name = acc.getName();
			Account update = this.accountService.update(accId,name);
			return ResponseEntity.ok(update);
		} catch (AccountNotFoundException ex) {
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/{accId}/closedAcount")
	public ResponseEntity<Void> closeAccount(@PathVariable("accId") long accId){
		try {
			this.accountService.closeAccount(accId);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
}
//                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              