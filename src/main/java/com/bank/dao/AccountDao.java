package com.bank.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.entity.Account;

public interface AccountDao extends JpaRepository<Account, Long> {

	
	@Query("SELECT a FROM Account a WHERE a.customer.cId = :customerId")
	List<Account> findByCustomerCId(@Param("customerId") Long customerId);
}
