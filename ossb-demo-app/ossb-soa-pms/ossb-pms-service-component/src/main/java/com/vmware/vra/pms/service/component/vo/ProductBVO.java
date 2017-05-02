package com.vmware.vra.pms.service.component.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Nehan
 *
 */

@XmlRootElement(name="product")
public class ProductBVO {

	private Integer productId;
	private String productName;
	private List<ProductPriceBVO> productPriceBVOList = new ArrayList<ProductPriceBVO>();
	
	/**
	 * @return the productId
	 */
	@XmlElement(name="productid")
	public Integer getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	/**
	 * @return the productName
	 */
	@XmlElement(name="productname")
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * @return the productPriceBVOList
	 */
	@XmlElementWrapper(name="productprices")
	@XmlElement(name="productprice")
	public List<ProductPriceBVO> getProductPriceBVOList() {
		return productPriceBVOList;
	}
	/**
	 * @param productPriceBVOList the productPriceBVOList to set
	 */
	public void setProductPriceBVOList(
			List<ProductPriceBVO> productPriceBVOList) {
		this.productPriceBVOList = productPriceBVOList;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductBVO [productId=" + productId + ", productName="
				+ productName + ", productPriceBVOList="
				+ productPriceBVOList + "]";
	}

}
