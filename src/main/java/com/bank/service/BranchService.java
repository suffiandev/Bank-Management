package com.bank.service;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dao.BranchDao;
import com.bank.entity.Branch;
import com.bank.exception.BranchNotFoundException;

@Service
public class BranchService {
	
	@Autowired
	private BranchDao branchDao;
	
	public Branch addBranch(Branch branch) {
		return this.branchDao.save(branch);
	}
	
	public Branch getBranchByBranchCode(Long branchCode) {
		return branchDao.findById(branchCode)
				.orElseThrow(()-> new BranchNotFoundException("Branch Not Found with Id: " + branchCode));
	}
	
	public List<Branch> getAllBranch(){
		return this.branchDao.findAll();
	}
	
	public Branch updateBranch(String name,String location,Long bCode) {
		Branch findByBranchCode = this.branchDao.findById(bCode)
				.orElseThrow(()-> new BranchNotFoundException("Branch Not Found with Id: " + bCode));
		findByBranchCode.setLocation(location);
		findByBranchCode.setName(name);
		this.branchDao.save(findByBranchCode);
		return findByBranchCode;
	}
	
	
	

}
