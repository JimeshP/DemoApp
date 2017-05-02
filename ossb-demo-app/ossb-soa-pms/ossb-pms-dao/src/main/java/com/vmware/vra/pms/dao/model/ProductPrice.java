package com.vmware.vra.pms.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="product_price")
public class ProductPrice {
	@Id
	@GeneratedValue
	@Column(name="PRD_PRICE_ID")
	private Integer productPriceId;
	
	@Column(name="PRICE_PER_UNIT",nullable=false)
	private Integer pricePerUnit;
	
	@ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
	@JoinColumn(name="Product_PROD_ID",nullable=false)
	private Product product;

	
	/**
	 * @return the productPriceId
	 */
	public Integer getProductPriceId() {
		return productPriceId;
	}

	/**
	 * @param productPriceId the productPriceId to set
	 */
	public void setProductPriceId(Integer productPriceId) {
		this.productPriceId = productPriceId;
	}

	/**
	 * @return the pricePerUnit
	 */
	public Integer getPricePerUnit() {
		return pricePerUnit;
	}

	/**
	 * @param pricePerUnit the pricePerUnit to set
	 */
	public void setPricePerUnit(Integer pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
}
