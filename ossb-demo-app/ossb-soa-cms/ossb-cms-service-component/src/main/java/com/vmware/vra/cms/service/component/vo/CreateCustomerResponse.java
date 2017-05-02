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
@XmlRootElement(name="createcustomerresponse")
public class CreateCustomerResponse extends StatusBVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4985425567338833046L;
	private CustomerBVO customerBVO;
	/**
	 * @return the customerBVO
	 */
	@XmlElement(name="customer")
	public CustomerBVO getCustomerBVO() {
		return customerBVO;
	}
	/**
	 * @param customerBVO the customerBVO to set
	 */
	public void setCustomerBVO(CustomerBVO customerBVO) {
		this.customerBVO = customerBVO;
	}
	
	

}
