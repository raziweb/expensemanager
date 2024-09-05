package com.finance.expensemanager.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.finance.expensemanager.exception.CategoryNotFoundException;
import com.finance.expensemanager.exception.TransactionNotFoundException;
import com.finance.expensemanager.model.dto.TransactionDTO;
import com.finance.expensemanager.model.entity.Category;
import com.finance.expensemanager.model.entity.Transaction;
import com.finance.expensemanager.model.entity.User;
import com.finance.expensemanager.repository.CategoryRepository;
import com.finance.expensemanager.repository.TransactionRepository;
import com.finance.expensemanager.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional
	public Transaction createTransaction(TransactionDTO transactionDTO) {
		Category category = categoryRepository.findById(transactionDTO.getCategoryId())
				.orElseThrow(() -> new CategoryNotFoundException("Category Not found"));
		
		User user = userRepository.findById(transactionDTO.getUserId())
				.orElseThrow(() -> new BadCredentialsException("User not found"));

		System.out.println(category.toString());

		Transaction transaction = new Transaction();
		transaction.setAmount(transactionDTO.getAmount());
		transaction.setDate(transactionDTO.getDate());
		transaction.setNote(transactionDTO.getNote());
		transaction.setCategory(category);
		transaction.setUser(user);

		return transactionRepository.save(transaction);
	}

	@Transactional
	public Transaction editTransaction(long id, TransactionDTO transactionDTO) {
		Transaction transaction = transactionRepository.findById(id)
				.orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));
		if (transaction.getCategory().getId() != transactionDTO.getCategoryId()) {
			Category category = categoryRepository.findById(transactionDTO.getCategoryId())
					.orElseThrow(() -> new CategoryNotFoundException("Category Not found"));

			transaction.setCategory(category);
		}	
		transaction.setAmount(transactionDTO.getAmount());
		transaction.setDate(transactionDTO.getDate());
		transaction.setNote(transactionDTO.getNote());

		return transactionRepository.save(transaction);
	}

	@Transactional
	public int deleteTransaction(long id) {
		Transaction transaction = transactionRepository.findById(id)
				.orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));
		transactionRepository.deleteById(id);
		return 1;
	}
	
	public List<Transaction> getAllTransactions() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		User user = userRepository.findByUsername(userDetails.getUsername());
		return transactionRepository.findByUserId(user.getId());
	}
	
	public List<Transaction> getCurrentMonthTransactions() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		User user = userRepository.findByUsername(userDetails.getUsername());
		
		LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();  
        int currentYear = currentDate.getYear();
        
        return transactionRepository.getCurrentMonthTransactions(user.getId(), currentMonth, currentYear);
	}
	
	public List<Transaction> getTransactionBetweenDates(String fromDate, String toDate) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		User user = userRepository.findByUsername(userDetails.getUsername());
		
		return transactionRepository.getTransactionBetweenDates(user.getId(), fromDate, toDate);
	}
	
	public List<Transaction> getTransactionsForGivenMonth(int year, int month) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		User user = userRepository.findByUsername(userDetails.getUsername());
		
		return transactionRepository.getTransactionsForGivenMonth(user.getId(), year, month);
	}
} 
