package com.finance.expensemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class}
        )
public class ExpensemanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpensemanagerApplication.class, args);
		System.out.println("******************Application started on port 8080**********************");
		System.out.println("******************Application started on port 8080**********************");
		System.out.println("******************Application started on port 8080**********************");
		System.out.println("******************Application started on port 8080**********************");
		System.out.println("******************Application started on port 8080**********************");
	}

}
