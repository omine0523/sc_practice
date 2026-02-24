package com.example.demo.exception;

public class BookAlreadyDeletedException extends RuntimeException {
	public BookAlreadyDeletedException(String message) {
		super(message);
	}
}
