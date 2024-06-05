package com.finance.expensemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.expensemanager.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
