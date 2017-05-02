package com.vmware.vra.oms.service.component.exception;

public class OMSServiceException extends Exception {
	
	private String errorCode;
	private String exceptionMessage;
	private Throwable throwable;
	
	public OMSServiceException() {
		
	}
	
	public OMSServiceException(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	
	public OMSServiceException(String errorCode, String exceptionMessage, Throwable throwable) {
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