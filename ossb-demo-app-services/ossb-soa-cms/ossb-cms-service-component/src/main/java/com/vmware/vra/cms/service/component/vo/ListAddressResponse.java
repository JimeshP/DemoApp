/**
 * 
 */
package com.vmware.vra.cms.service.component.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author RamS
 *
 */
@XmlRootElement(name="listaddressresponse")
public class ListAddressResponse extends StatusBVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2293914359378415305L;


	private List<AddressBVO> addresslist;

	/**
	 * @return the addresslist
	 */
	@XmlElementWrapper(name="addresss")
	@XmlElement(name="address")
	public List<AddressBVO> getAddresslist() {
		return addresslist;
	}

	/**
	 * @param addresslist the addresslist to set
	 */
	public void setAddresslist(List<AddressBVO> addresslist) {
		this.addresslist = addresslist;
	}
	
	
}
