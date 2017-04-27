package com.vmware.vra.pms.service.component.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Nehan
 *
 */
@XmlRootElement(name="productprice")
public class ProductPriceBVO {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductPriceBVO [productPriceId=" + productPriceId
				+ ", pricePerUnit=" + pricePerUnit + ", productPRODID="
				+ productPRODID + "]";
	}
	private Integer productPriceId;
	private Integer pricePerUnit;
	private Integer productPRODID;
	/**
	 * @return the product_Price_Id
	 */
	@XmlElement(name="productpriceid")
	public Integer getProductPriceId() {
		return productPriceId;
	}
	/**
	 * @param product_Price_Id the product_Price_Id to set
	 */
	public void setProductPriceId(Integer productPriceId) {
		this.productPriceId = productPriceId;
	}
	/**
	 * @return the price_Per_Unit
	 */
	@XmlElement(name="priceperunit")
	public Integer getPricePerUnit() {
		return pricePerUnit;
	}
	/**
	 * @param price_Per_Unit the price_Per_Unit to set
	 */
	public void setPricePerUnit(Integer pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	/**
	 * @return the product_PROD_ID
	 */
	@XmlElement(name="productid")
	public Integer getProductPRODID() {
		return productPRODID;
	}
	/**
	 * @param product_PROD_ID the product_PROD_ID to set
	 */
	public void setProductPRODID(Integer productPRODID) {
		this.productPRODID = productPRODID;
	}
}
