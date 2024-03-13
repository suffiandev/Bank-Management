package com.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bank.entity.Customer;


public interface CustomerDao extends JpaRepository<Customer, Long> {
	
//	@Query("SELECT c FROM Customer c WHERE c.cId = :customerId")
//    Customer findByCId(long customerId);
	
	
	
	public Customer findByEmail(String email);
}



//@Query("select c from Customer c where c.name = :name")
//public Customer getCustomerByCustomerId(String name);