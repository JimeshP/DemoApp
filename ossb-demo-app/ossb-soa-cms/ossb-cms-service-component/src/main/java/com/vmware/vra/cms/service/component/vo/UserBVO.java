/**
 * 
 */
package com.vmware.vra.cms.service.component.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author RamS
 *
 */
@XmlRootElement(name="user")
public class UserBVO {

	private Integer userId;
	private String userName;
	private String password;
	private Integer customerId;

	/**
	 * @return the userId
	 */
	@XmlElement(name="userid")
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	@XmlElement(name="username")
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	@XmlElement(name="password")
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the customerId
	 */
	@XmlElement(name="customerid")
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserBVO [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + ", customerId=" + customerId + "]";
	}
	
	
}
