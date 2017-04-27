package com.vmware.vra.pms.dao.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="product")
public class Product {
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName="
				+ productName + ", productPriceList=" + productPriceList + "]";
	}

	@Id
	@GeneratedValue
	@Column(name="PROD_ID")
	private Integer productId;
	
	@Column(name="PROD_NAME")
	private String productName;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="product", fetch = FetchType.EAGER) //, targetEntity=OrderLine.class, fetch = FetchType.EAGER)
	private List<ProductPrice> productPriceList;

	/**
	 * @return the productPriceList
	 */
	public List<ProductPrice> getProductPriceList() {
		return productPriceList;
	}

	/**
	 * @param productPriceList the productPriceList to set
	 */
	public void setProductPriceList(List<ProductPrice> productPriceList) {
		this.productPriceList = productPriceList;
	}

	/**
	 * @return the productId
	 */
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
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}


}
