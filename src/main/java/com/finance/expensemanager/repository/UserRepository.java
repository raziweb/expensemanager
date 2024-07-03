package com.finance.expensemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.expensemanager.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
