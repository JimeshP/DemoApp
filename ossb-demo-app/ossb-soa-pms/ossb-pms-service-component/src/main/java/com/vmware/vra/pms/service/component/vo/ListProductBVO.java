package com.vmware.vra.pms.service.component.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Nehan
 *
 */
@XmlRootElement(name="productlist")
public class ListProductBVO extends StatusBVO{
	private static final long serialVersionUID = -903315441411196997L;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ListProductBVO [productList=" + productList + "]";
	}

	private List<ProductBVO> productList;

	/**
	 * @return the productList
	 */
	@XmlElementWrapper(name="products")
	@XmlElement(name="product")
	public List<ProductBVO> getProductList() {
		return productList;
	}

	/**
	 * @param productList the productList to set
	 */
	public void setProductList(List<ProductBVO> productList) {
		this.productList = productList;
	}
}
