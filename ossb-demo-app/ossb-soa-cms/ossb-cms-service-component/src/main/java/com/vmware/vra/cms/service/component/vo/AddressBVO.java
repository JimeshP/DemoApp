/**
 * 
 */
package com.vmware.vra.cms.service.component.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author RamS
 *
 */
@XmlRootElement(name="address")
public class AddressBVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1158328097585036517L;
	private Integer addressId;
	private Integer customerId;
	private String addressFirstLine;
	private String addressSecondLine;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	/**
	 * @return the addressId
	 */
	@XmlElement(name="addressid")
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
	 * @return the addressFirstLine
	 */
	@XmlElement(name="addressfirstline")
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
	@XmlElement(name="addresssecondline")
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
	@XmlElement(name="city")
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
	@XmlElement(name="state")
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
	@XmlElement(name="country")
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
	@XmlElement(name="zipcode")
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
		return "AddressBVO [addressId=" + addressId + ", customerId="
				+ customerId + ", addressFirstLine=" + addressFirstLine
				+ ", addressSecondLine=" + addressSecondLine + ", city=" + city
				+ ", state=" + state + ", country=" + country + ", zipCode="
				+ zipCode + "]";
	}

}
