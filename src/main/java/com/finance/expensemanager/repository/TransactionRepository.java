package com.finance.expensemanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finance.expensemanager.model.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>  {
	List<Transaction> findByUserId(int userId);
}
