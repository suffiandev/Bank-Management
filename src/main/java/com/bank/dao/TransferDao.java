package com.bank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.entity.Transfer;

public interface TransferDao extends JpaRepository<Transfer, Long> {
	
	@Query("SELECT t FROM Transfer t where t.sourceAccount.id = :accId")
	List<Transfer> findByAccountId(@Param("accId") long accId);

}
