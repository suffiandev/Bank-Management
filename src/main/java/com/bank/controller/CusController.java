package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bank.entity.Customer;
import com.bank.exception.CustomerNotFoundException;
import com.bank.service.CustomerService;

@RestController
public class CusController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/home")
	public String home() {
		return "This is home page";
	}
	
	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
		
		try {
			this.customerService.add(customer);
			return ResponseEntity.status(HttpStatus.CREATED).body(customer);
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@GetMapping("/getAllCustomer")
	public ResponseEntity<List<Customer>> getAllCustomer(){
		try {
			List<Customer> allCustomer = this.customerService.getAllCustomer();
			if(allCustomer == null || allCustomer.isEmpty() ) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.ok(allCustomer);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@GetMapping("/{cId}/getCustomerById")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("cId") Long cId){
		try {
			Customer customerById = customerService.getCustomerById(cId);
			if(customerById == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			return ResponseEntity.ok(customerById);
		} catch (CustomerNotFoundException ex) {
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
//	two ways weather i use request Body or requestParam
	@PutMapping("/{cId}/updateCustomer")
	public ResponseEntity<Customer> UpdateCustomer(@PathVariable("cId") Long cId, String name, @RequestBody Customer cus){
		try {
			String newName = cus.getName();
			String newEmail = cus.getEmail();
			Customer updateCustomer = this.customerService.updateCustomer(cId,newName,newEmail);
			if(updateCustomer == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.ok(updateCustomer);
		  
		} catch (CustomerNotFoundException ex) {
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/{cId}/delete")
	public ResponseEntity<Customer> deleteACustomer(@PathVariable("cId") Long cId){
		try {
			Customer deleteACustomer = this.customerService.deleteACustomer(cId);
			return ResponseEntity.ok(deleteACustomer);
			
		} catch (CustomerNotFoundException ex) {
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	
	

}




//Customer customer = this.customerService.getCustomerById(name);
//account.setCustomer(customer);
//customer.getAccounts().add(account);
