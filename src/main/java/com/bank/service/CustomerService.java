package com.bank.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bank.dao.CustomerDao;
import com.bank.entity.Customer;
import com.bank.exception.CustomerNotFoundException;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public Customer add(Customer customer) {
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		Customer save = this.customerDao.save(customer);
		return save;
	}
	
	
	
	public Customer getCustomerById(Long cId) {
		return this.customerDao.findById(cId)
				.orElseThrow(()-> new CustomerNotFoundException("Customer Not Found With Id: " + cId));
	}
	
	public List<Customer> getAllCustomer() {
		List<Customer> findAll = customerDao.findAll();
		return findAll;
	}
	
	public Customer updateCustomer(Long cId,String name,String email) {
		Customer customerById = this.getCustomerById(cId);
		customerById.setName(name);
		customerById.setEmail(email);
		Customer save = customerDao.save(customerById);
		return save;
	}
	
	public Customer deleteACustomer(Long cId) {
		Customer delete = this.getCustomerById(cId);
		customerDao.delete(delete);
		return delete;
	}
	
	
	

}


//public Customer getCustomerById(String name) {
//	Customer id = this.customerDao.getCustomerByCustomerId(name);
//	return id;
//}
