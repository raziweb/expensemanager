package com.finance.expensemanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.expensemanager.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	List<Category> findByUserId(int userId);

}
