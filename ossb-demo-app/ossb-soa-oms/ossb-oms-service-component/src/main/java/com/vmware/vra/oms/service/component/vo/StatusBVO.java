package com.vmware.vra.oms.service.component.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class StatusBVO implements Serializable {

	private String errorCode;
	private String message;
	private String status;
	
	/**
	 * @return the errorCode
	 */
	@XmlElement(name="errorCode")
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @return the errorDesc
	 */
	@XmlElement(name="message")
	public String getMessage() {
		return message;
	}
	/**
	 * @param errorDesc the errorDesc to set
	 */
	public void setMessage(String errorDesc) {
		this.message = errorDesc;
	}
	/**
	 * @return the status
	 */
	@XmlElement(name="status")
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StatusBVO [errorCode=" + errorCode + ", errorDesc=" + message
				+ ", status=" + status + "]";
	}
	
	
	
}
