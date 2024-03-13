package com.bank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.entity.Loan;

public interface LoanDao extends JpaRepository<Loan, Long> {

	
	@Query("SELECT l FROM Loan l where l.customer.cId = :cId")
	List<Loan> findByCustomerCId(@Param("cId") Long cId);
}
