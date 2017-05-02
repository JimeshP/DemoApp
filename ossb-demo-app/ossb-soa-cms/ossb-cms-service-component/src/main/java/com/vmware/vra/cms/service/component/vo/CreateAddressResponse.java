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

@XmlRootElement(name="createaddressresponse")
public class CreateAddressResponse extends StatusBVO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6050054829024837961L;

	private AddressBVO addressBVO;

	/**
	 * @return the addressBVO
	 */
	@XmlElement(name="address")
	public AddressBVO getAddressBVO() {
		return addressBVO;
	}

	/**
	 * @param addressBVO the addressBVO to set
	 */
	public void setAddressBVO(AddressBVO addressBVO) {
		this.addressBVO = addressBVO;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CreateAddressResponse [addressBVO=" + addressBVO + "]";
	}
}
