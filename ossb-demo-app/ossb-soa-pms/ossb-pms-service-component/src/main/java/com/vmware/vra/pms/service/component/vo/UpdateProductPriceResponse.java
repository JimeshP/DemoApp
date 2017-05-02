package com.vmware.vra.pms.service.component.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author NehaN
 *
 */
@XmlRootElement(name="updateproductpriceresponse")
public class UpdateProductPriceResponse extends StatusBVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3590097684774407714L;
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
