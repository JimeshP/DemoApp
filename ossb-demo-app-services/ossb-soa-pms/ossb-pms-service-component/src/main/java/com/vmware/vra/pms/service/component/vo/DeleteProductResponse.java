package com.vmware.vra.pms.service.component.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author NehaN
 *
 */
@XmlRootElement(name="deleteproductresponse")
public class DeleteProductResponse extends StatusBVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5641179044012720716L;
	private ProductBVO productBVO;

	/**
	 * @return the productBVO
	 */
	@XmlElement(name="product")
	public ProductBVO getProductBVO() {
		return productBVO;
	}
	/**
	 * @param productBVO the productBVO to set
	 */
	public void setProductBVO(ProductBVO productBVO) {
		this.productBVO = productBVO;
	}
}
