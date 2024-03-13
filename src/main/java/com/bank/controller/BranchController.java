package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.Branch;
import com.bank.exception.BranchNotFoundException;
import com.bank.exception.TransactionNotFoundException;
import com.bank.service.BranchService;

@RestController
public class BranchController {
	
	@Autowired
	private BranchService branchService;
	
	
	@PostMapping("/addBranch")
	public ResponseEntity<Branch> addBranch(@RequestBody Branch branch){
		try {
			Branch addBranch = this.branchService.addBranch(branch);
			return ResponseEntity.ok(addBranch);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{bCode}/getByBranchCode")
	public ResponseEntity<Branch> getBranchId(@PathVariable("bCode") Long bCode){
		try {
			Branch branchByBranchCode = this.branchService.getBranchByBranchCode(bCode);
			return ResponseEntity.ok(branchByBranchCode);
		} catch (BranchNotFoundException ex) {
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/getAllBranch")
	public ResponseEntity<List<Branch>> getAllBranch(){
		try {
			List<Branch> allBranch = this.branchService.getAllBranch();
			return ResponseEntity.ok(allBranch);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/{bCode}/updateBranch")
	public ResponseEntity<Branch> updateBranch( @PathVariable("bCode") Long bCode ,@RequestBody Branch branch){
		try {
			String name = branch.getName();
			String location = branch.getLocation();
			Branch updateCustomer = this.branchService.updateBranch(name, location, bCode);
			return ResponseEntity.ok(updateCustomer);
		} catch (BranchNotFoundException ex) {
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
