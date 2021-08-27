package com.revature.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dto.MessageDTO;
import com.revature.exception.InvalidCredentialException;
import com.revature.exception.InvalidInputException;

import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;

public class ExceptionController implements Controller{
	
	private Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	private ExceptionHandler<InvalidInputException> invalidInputExceptionHandler = (e, ctx) -> {
		logger.info("Exception Occured: Exception message is " + e.getMessage());
		
		ctx.status(400);
		ctx.json(new MessageDTO(e.getMessage()));
	};
	
	private ExceptionHandler<InvalidCredentialException> invalidCredentialExceptionHandler = (e, ctx) -> {
		logger.info("Exception Occured: Exception message is " + e.getMessage());
		
		ctx.status(400);
		ctx.json(new MessageDTO(e.getMessage()));
	};


	@Override
	public void mapEndpoints(Javalin app) {
		app.exception(InvalidInputException.class, invalidInputExceptionHandler);
		app.exception(InvalidCredentialException.class, invalidCredentialExceptionHandler);
	}

}
