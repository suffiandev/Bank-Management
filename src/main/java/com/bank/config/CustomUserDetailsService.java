package com.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bank.dao.CustomerDao;
import com.bank.entity.Customer;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private CustomerDao customerDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer customer = customerDao.findByEmail(email);
		if(customer==null) {
			 throw new UsernameNotFoundException("User Not Found");
		}
		return new CustomUser(customer);
	}

}
