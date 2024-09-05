package com.finance.expensemanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finance.expensemanager.model.dto.TransactionDTO;
import com.finance.expensemanager.model.entity.Transaction;
import com.finance.expensemanager.repository.TransactionRepository;
import com.finance.expensemanager.service.TransactionService;

@CrossOrigin(origins= {"*"}, maxAge = 4800)
@RestController
public class TransactionController {
	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private TransactionService transactionService;

	@GetMapping("/hello")
	public String hello() {
		return "Hello";
	}

	@PostMapping("/transactions")
	public Transaction addTransaction(@RequestBody TransactionDTO transactionDTO) {
		Transaction transaction = transactionService.createTransaction(transactionDTO);
		return transaction;
	}

	@GetMapping("/transactions")
	public List<Transaction> getAllTransactions() {
		return transactionService.getAllTransactions();
	}
	
	@GetMapping("/hometransactions") 
	public List<Transaction> getCurrentMonthTransactions() {
		return transactionService.getCurrentMonthTransactions();
	}

	@PutMapping("/transactions/{id}")
	public Transaction editTransaction(@PathVariable long id, @RequestBody TransactionDTO transactionDTO) {
		return transactionService.editTransaction(id, transactionDTO);
	}

	@DeleteMapping("/transactions/{id}")
	public int deleteTransaction(@PathVariable long id) {
		return transactionService.deleteTransaction(id);
	}
	
	@GetMapping("/transactions/{fromDate}/to/{toDate}")
	public List<Transaction> getTransactionBetweenDates(@PathVariable String fromDate, @PathVariable String toDate){
		return transactionService.getTransactionBetweenDates(fromDate, toDate);
	}
	
	@GetMapping("/transactions/{year}/{month}")
	public List<Transaction> getTransactionsForGivenMonth(@PathVariable int year, @PathVariable int month) {
		return transactionService.getTransactionsForGivenMonth(year, month);
	}
}
