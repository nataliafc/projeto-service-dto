package com.serratec.backend.projetoServiceDto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.serratec.backend.projetoServiceDto.exception.EmailException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(EmailException.class)
	
	protected ResponseEntity<Object> handleEmailException(EmailException ex) {
		EmailException emailException = new EmailException(ex.getMessage());
		return ResponseEntity.unprocessableEntity()
.body(emailException);
		}
}
