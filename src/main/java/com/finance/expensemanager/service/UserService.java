package com.finance.expensemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.finance.expensemanager.exception.EntityValidationException;
import com.finance.expensemanager.model.entity.User;
import com.finance.expensemanager.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User registerUser(User user) {
		try {
			return userRepository.save(user);
		}
		catch(DataIntegrityViolationException e) {
			throw new EntityValidationException("Duplicate value for username");
		}
		
	}
}
