package com.vmware.vra.pms.service.component.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author NehaN
 *
 */
@XmlRootElement(name="createproductpriceresponse")
public class CreateProductPriceResponse extends StatusBVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5852029578350337304L;

	private ProductPriceBVO productPriceBVO;
	/**
	 * @return the productPriceBVO
	 */
	@XmlElement(name="productprice")
	public ProductPriceBVO getProductPriceBVO() {
		return productPriceBVO;
	}
	/**
	 * @param productPriceBVO the productPriceBVO to set
	 */
	public void setProductPriceBVO(ProductPriceBVO productPriceBVO) {
		this.productPriceBVO = productPriceBVO;
	}

}
