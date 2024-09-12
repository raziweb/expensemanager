package com.finance.expensemanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finance.expensemanager.model.dto.CategoryDTO;
import com.finance.expensemanager.model.entity.Category;
import com.finance.expensemanager.service.CategoryService;

@RestController
@CrossOrigin(origins= {"*"}, maxAge = 4800)
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/category")
	public Category addCategory(@RequestBody CategoryDTO categoryDTO) {
		return categoryService.addCategory(categoryDTO);
	}
	
	@GetMapping("/category")
	public List<Category> getAllCategoriesForCurrentUser() {
		return categoryService.getAllCategoriesForCurrentUser();
	}
}
