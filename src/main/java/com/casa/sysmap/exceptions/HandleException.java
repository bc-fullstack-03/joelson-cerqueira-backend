package com.casa.sysmap.exceptions;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class HandleException {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<Wrong> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		Wrong wrong = new Wrong(LocalDate.now(), HttpStatus.NOT_FOUND.value(), e.getMessage(),
				request.getRequestURI());
		return new ResponseEntity<Wrong>(wrong, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Wrong> argumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
		Wrong wrong = new Wrong(LocalDate.now(), HttpStatus.BAD_REQUEST.value(), "argument no valid.",
				request.getRequestURI());

		e.getBindingResult().getFieldErrors().forEach(fieldError -> {
			wrong.setField(fieldError.getField(), fieldError.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(wrong);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Wrong> mismatch(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
		Wrong wrong = new Wrong(LocalDate.now(), HttpStatus.BAD_REQUEST.value(), "Incorrect id",
				request.getRequestURI());
		return new ResponseEntity<Wrong>(wrong, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Wrong> nullPointer(NullPointerException e, HttpServletRequest request) {
		Wrong wrong = new Wrong(LocalDate.now(), HttpStatus.BAD_REQUEST.value(), "Null pointer exception",
				request.getRequestURI());
		return new ResponseEntity<Wrong>(wrong, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Wrong> noSuchElement(NoSuchElementException e, HttpServletRequest request) {
		Wrong wrong = new Wrong(LocalDate.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				request.getRequestURI());
		return new ResponseEntity<Wrong>(wrong, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Wrong> illegalArgument(IllegalArgumentException e, HttpServletRequest request) {
		Wrong wrong = new Wrong(LocalDate.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				request.getRequestURI());
		return new ResponseEntity<Wrong>(wrong, HttpStatus.BAD_REQUEST);
	}
}
