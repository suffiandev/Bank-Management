package com.bank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.entity.Transaction;

public interface TransactionDao extends JpaRepository<Transaction,Long> {
	
//	void deleteById(long id);
	
	@Query("SELECT t FROM Transaction t where t.customer.cId = :cusId")
	List<Transaction> findByCustomerId(@Param("cusId") Long cusId);

}
