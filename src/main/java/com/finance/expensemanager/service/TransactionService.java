package com.finance.expensemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.expensemanager.exception.CategoryNotFoundException;
import com.finance.expensemanager.exception.TransactionNotFoundException;
import com.finance.expensemanager.model.dto.TransactionDTO;
import com.finance.expensemanager.model.entity.Category;
import com.finance.expensemanager.model.entity.Transaction;
import com.finance.expensemanager.repository.CategoryRepository;
import com.finance.expensemanager.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional
	public Transaction createTransaction(TransactionDTO transactionDTO) {
		Category category = categoryRepository.findById(transactionDTO.getCategoryId())
				.orElseThrow(() -> new CategoryNotFoundException("Category Not found"));

		System.out.println(category.toString());

		Transaction transaction = new Transaction();
		transaction.setAmount(transactionDTO.getAmount());
		transaction.setDate(transactionDTO.getDate());
		transaction.setNote(transactionDTO.getNote());
		transaction.setCategory(category);

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
}
