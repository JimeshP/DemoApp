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
@XmlRootElement(name="deleteaddressresponse")
public class DeleteAddressResponse extends StatusBVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1521071095465206352L;
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
		return "DeleteAddressResponse [addressBVO=" + addressBVO + "]";
	}

}
