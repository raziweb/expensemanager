package com.finance.expensemanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.finance.expensemanager.model.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>  {
	List<Transaction> findByUserId(int userId);
	
	@Query(value = "SELECT * FROM transaction WHERE user = ?1 AND MONTH(date) = ?2 AND YEAR(date) = ?3 ORDER BY date", nativeQuery = true)
	List<Transaction> getCurrentMonthTransactions(int userId, int month, int year);
	
	@Query(value = "SELECT * FROM transaction WHERE user = ?1 AND date BETWEEN ?2 AND ?3 ORDER BY date", nativeQuery = true)
	List<Transaction> getTransactionBetweenDates(int userId, String fromDate, String toDate);
	
	@Query(value = "SELECT * FROM transaction WHERE user = ?1 AND YEAR(date) = ?2 AND MONTH(date) = ?3 ORDER BY date", nativeQuery = true)
	List<Transaction> getTransactionsForGivenMonth(int userId, int year, int month);
}
