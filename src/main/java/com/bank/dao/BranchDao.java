package com.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.entity.Branch;

public interface BranchDao extends JpaRepository<Branch, Long> {
	

}
