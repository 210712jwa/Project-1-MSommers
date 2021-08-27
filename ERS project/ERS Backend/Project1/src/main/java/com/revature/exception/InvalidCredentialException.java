package com.revature.exception;

public class InvalidCredentialException extends Exception {

	public InvalidCredentialException() {
	}

	public InvalidCredentialException(String message) {
		super(message);
	}

	public InvalidCredentialException(Throwable cause) {
		super(cause);
	}

	public InvalidCredentialException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidCredentialException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
