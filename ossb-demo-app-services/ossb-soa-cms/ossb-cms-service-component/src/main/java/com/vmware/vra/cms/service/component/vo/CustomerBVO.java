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
@XmlRootElement(name="customer")
public class CustomerBVO {
	

	private Integer customerId;
	private String customerStatus;
	private String customerFirstName;
	private String customerLastName;
	private AddressBVO address;
	private UserBVO	user;
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
	/**
	 * @return the customerStatus
	 */
	@XmlElement(name="customerstatus")
	public String getCustomerStatus() {
		return customerStatus;
	}
	/**
	 * @param customerStatus the customerStatus to set
	 */
	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	
	/**
	 * @return the customerFirstName
	 */
	@XmlElement(name="customerfirstname")
	public String getCustomerFirstName() {
		return customerFirstName;
	}
	/**
	 * @param customerFirstName the customerFirstName to set
	 */
	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}
	/**
	 * @return the customerLastName
	 */
	@XmlElement(name="customerlastname")
	public String getCustomerLastName() {
		return customerLastName;
	}
	/**
	 * @param customerLastName the customerLastName to set
	 */
	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}
	/**
	 * @return the address
	 */
	@XmlElement(name="address")
	public AddressBVO getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(AddressBVO address) {
		this.address = address;
	}
	/**
	 * @return the user
	 */
	
	@XmlElement(name="user")
	public UserBVO getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(UserBVO user) {
		this.user = user;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerBVO [customerId=" + customerId + ", customerStatus="
				+ customerStatus + ", customerFirstName=" + customerFirstName
				+ ", customerLastName=" + customerLastName + ", address="
				+ address + ", user=" + user + "]";
	}
	
}
