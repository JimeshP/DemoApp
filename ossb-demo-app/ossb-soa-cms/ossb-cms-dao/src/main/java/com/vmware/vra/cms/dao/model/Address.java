/**
 * 
 */
package com.vmware.vra.cms.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author RamS
 *
 */

@Entity
@Table(name="address")
public class Address {
	
	@Id
	@GeneratedValue
	@Column(name="ADD_ID")
	private Integer addressId;
	
	@ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY)
	@JoinColumn(name="Customer_CUST_ID",nullable=false)
	private Customer customer;
	
	@Column(name="ADD_LINE_1",nullable=false)
	private String addressFirstLine;
	
	@Column(name="ADD_LINE_2",nullable=true)
	private String addressSecondLine;
	
	@Column(name="CITY",nullable=false)
	private String city;

	@Column(name="STATE",nullable=false)
	private String state;
	
	@Column(name="COUNTRY",nullable=false)
	private String country;
	
	@Column(name="ZIPCODE",nullable=false)
	private String zipCode;

	/**
	 * @return the addressId
	 */
	public Integer getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the addressFirstLine
	 */
	public String getAddressFirstLine() {
		return addressFirstLine;
	}

	/**
	 * @param addressFirstLine the addressFirstLine to set
	 */
	public void setAddressFirstLine(String addressFirstLine) {
		this.addressFirstLine = addressFirstLine;
	}

	/**
	 * @return the addressSecondLine
	 */
	public String getAddressSecondLine() {
		return addressSecondLine;
	}

	/**
	 * @param addressSecondLine the addressSecondLine to set
	 */
	public void setAddressSecondLine(String addressSecondLine) {
		this.addressSecondLine = addressSecondLine;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", customer=" + customer
				+ ", addressFirstLine=" + addressFirstLine
				+ ", addressSecondLine=" + addressSecondLine + ", city=" + city
				+ ", state=" + state + ", country=" + country + ", zipCode="
				+ zipCode + "]";
	}
}
