package com.vmware.vra.pms.service.component.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="productpricelist")
public class ListProductPriceBVO extends StatusBVO{

	private static final long serialVersionUID = 8957972364833611437L;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ListProductPriceBVO [productPriceList=" + productPriceList
				+ "]";
	}

	private List<ProductPriceBVO> productPriceList;

	/**
	 * @return the productPriceList
	 */
	@XmlElementWrapper(name="productprices")
	@XmlElement(name="productprice")
	public List<ProductPriceBVO> getProductPriceList() {
		return productPriceList;
	}

	/**
	 * @param productPriceList the productPriceList to set
	 */
	public void setProductPriceList(List<ProductPriceBVO> productPriceList) {
		this.productPriceList = productPriceList;
	}
	
}
