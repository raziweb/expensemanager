package com.finance.expensemanager.exception;

public class EntityValidationException extends RuntimeException {
	public EntityValidationException (String message) {
		super(message);
	}
}
