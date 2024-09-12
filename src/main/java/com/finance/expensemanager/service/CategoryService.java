package com.finance.expensemanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.finance.expensemanager.model.dto.CategoryDTO;
import com.finance.expensemanager.model.entity.Category;
import com.finance.expensemanager.model.entity.User;
import com.finance.expensemanager.repository.CategoryRepository;
import com.finance.expensemanager.repository.UserRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Category addCategory(CategoryDTO categoryDTO) {
		User user = userRepository.findById(categoryDTO.getUserId())
				.orElseThrow(() -> new BadCredentialsException("User not found"));
		
		Category category = new Category();
		category.setName(categoryDTO.getName());
		category.setType(categoryDTO.getType());
		category.setUser(user);
		
		return categoryRepository.save(category);
	}
	
	public List<Category> getAllCategoriesForCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		User user = userRepository.findByUsername(userDetails.getUsername());
		return categoryRepository.findByUserId(user.getId());
	}
	
	public void addDefaultCategories(User newUser) {
		Category category1 = new Category();
		category1.setName("Shopping");
		category1.setType("expense");
		category1.setUser(newUser);
		
		Category category2 = new Category();
		category2.setName("Food/Grocery");
		category2.setType("expense");
		category2.setUser(newUser);
		
		Category category3 = new Category();
		category3.setName("Travelling");
		category3.setType("expense");
		category3.setUser(newUser);
		
		Category category4 = new Category();
		category4.setName("Entertainment");
		category4.setType("expense");
		category4.setUser(newUser);
		
		Category category5 = new Category();
		category5.setName("Medical");
		category5.setType("expense");
		category5.setUser(newUser);
		
		Category category6 = new Category();
		category6.setName("Personal Care");
		category6.setType("expense");
		category6.setUser(newUser);
		
		Category category7 = new Category();
		category7.setName("Bills/Utilities");
		category7.setType("expense");
		category7.setUser(newUser);
		
		Category category8 = new Category();
		category8.setName("Investments");
		category8.setType("expense");
		category8.setUser(newUser);
		
		Category category9 = new Category();
		category9.setName("Rent");
		category9.setType("expense");
		category9.setUser(newUser);
		
		Category category10 = new Category();
		category10.setName("Gifts/Charity");
		category10.setType("expense");
		category10.setUser(newUser);
		
		Category category11 = new Category();
		category11.setName("Salary");
		category11.setType("income");
		category11.setUser(newUser);
		
		Category category12 = new Category();
		category12.setName("Freelancing");
		category12.setType("income");
		category12.setUser(newUser);
		
		Category category13 = new Category();
		category13.setName("Investment Income");
		category13.setType("income");
		category13.setUser(newUser);
		
		Category category14 = new Category();
		category14.setName("Business");
		category14.setType("income");
		category14.setUser(newUser);
		
		Category category15 = new Category();
		category15.setName("Others");
		category15.setType("income");
		category15.setUser(newUser);
		
		Category category16 = new Category();
		category16.setName("Others");
		category16.setType("expense");
		category16.setUser(newUser);
		
		List<Category> defaultCategories = new ArrayList<>();
		defaultCategories.add(category1);
		defaultCategories.add(category2);
		defaultCategories.add(category3);
		defaultCategories.add(category4);
		defaultCategories.add(category5);
		defaultCategories.add(category6);
		defaultCategories.add(category7);
		defaultCategories.add(category8);
		defaultCategories.add(category9);
		defaultCategories.add(category10);
		defaultCategories.add(category11);
		defaultCategories.add(category12);
		defaultCategories.add(category13);
		defaultCategories.add(category14);
		defaultCategories.add(category15);
		defaultCategories.add(category16);
		
		categoryRepository.saveAll(defaultCategories);
	}
}
