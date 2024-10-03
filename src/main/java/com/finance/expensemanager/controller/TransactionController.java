package com.finance.expensemanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@GetMapping(".well-known/pki-validation/65D017C3AA1E5E4652EB6FD5ABD23E73.txt")
	public ResponseEntity<String> getVerifyFile() {
		String fileContent = "790514BFB7824EE50D2A9310B0CF38C10D8286B1FE3C0ACC8304B599A36E0A52\n" +
				"comodoca.com\n" +
				"48ca672d30bd6fa";

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "text/plain");
		return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
	}
}
