package com.vmware.vra.pms.service.component.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class StatusBVO implements Serializable {

	private String errorCode;
	private String errorDesc;
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
	@XmlElement(name="errorDesc")
	public String getErrorDesc() {
		return errorDesc;
	}
	/**
	 * @param errorDesc the errorDesc to set
	 */
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
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
		return "StatusBVO [errorCode=" + errorCode + ", errorDesc=" + errorDesc
				+ ", status=" + status + "]";
	}
	
	
	
}
