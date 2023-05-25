package com.astralbrands.flight.x3.exception;

public class X3IntegrationException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public X3IntegrationException(String message) {
		super(message);
	}
	
	public X3IntegrationException(String message, Throwable thrw) {
		super(message,thrw);
	}

}
