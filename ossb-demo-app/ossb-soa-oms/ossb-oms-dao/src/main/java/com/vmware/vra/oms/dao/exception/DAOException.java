package com.vmware.vra.oms.dao.exception;

public class DAOException extends Exception {
	
	private String exceptionMessage;
	private String errorCode;
	private Throwable throwable;
	
	public DAOException() {
		
	}
	
	public DAOException(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	
	public DAOException(String errorCode, String exceptionMessage, Throwable throwable) {
		this.errorCode = errorCode;
		this.exceptionMessage = exceptionMessage;
		this.throwable = throwable;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

}
