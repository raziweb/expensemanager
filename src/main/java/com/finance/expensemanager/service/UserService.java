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
	
	@Autowired
	private CategoryService categoryService;
	
	public User registerUser(User user) {
		try {
			User newUser = userRepository.save(user);
			categoryService.addDefaultCategories(newUser);
			return newUser;
		}
		catch(DataIntegrityViolationException e) {
			throw new EntityValidationException("Duplicate value for username");
		}
		
	}
}
