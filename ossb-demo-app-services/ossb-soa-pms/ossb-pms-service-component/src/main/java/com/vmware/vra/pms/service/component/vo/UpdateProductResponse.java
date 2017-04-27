package com.vmware.vra.pms.service.component.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * @author NehaN
 *
 */
@XmlRootElement(name="updateproductresponse")
public class UpdateProductResponse extends StatusBVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 739178462492267102L;
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
