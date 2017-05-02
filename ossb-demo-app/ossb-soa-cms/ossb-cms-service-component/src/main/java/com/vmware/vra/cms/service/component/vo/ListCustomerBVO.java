/**
 * 
 */
package com.vmware.vra.cms.service.component.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author RamS
 *
 */
@XmlRootElement(name="listcustomerresponse")
public class ListCustomerBVO extends StatusBVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1687811709408702216L;
	private List<CustomerBVO> customerList;

	/**
	 * @return the customerList
	 */
	@XmlElementWrapper(name="customers")
	@XmlElement(name="customer")
	public List<CustomerBVO> getCustomerList() {
		return customerList;
	}

	/**
	 * @param customerList the customerList to set
	 */
	public void setCustomerList(List<CustomerBVO> customerList) {
		this.customerList = customerList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ListCustomerBVO [customerList=" + customerList + "]";
	}
}
