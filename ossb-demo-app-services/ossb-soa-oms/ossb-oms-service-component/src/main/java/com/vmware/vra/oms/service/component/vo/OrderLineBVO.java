package com.vmware.vra.oms.service.component.vo;

import javax.xml.bind.annotation.XmlElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderLineBVO {

	private Integer orderLineId;
	//private Integer orderHeaderId;
	private Integer productId;
	private Integer productPriceId;
	private Integer productPriceValue;

	
	@XmlElement(name="orderlineid")
	public Integer getOrderLineId() {
		return orderLineId;
	}
	public void setOrderLineId(Integer orderLineId) {
		this.orderLineId = orderLineId;
	}
	
	/*@XmlElement(name="orderheaderid")
	public Integer getOrderHeaderId() {
		return orderHeaderId;
	}
	public void setOrderHeaderId(Integer orderHeaderId) {
		this.orderHeaderId = orderHeaderId;
	}
	*/
	@XmlElement(name="productid")
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	@XmlElement(name="productpriceid")
	public Integer getProductPriceId() {
		return productPriceId;
	}
	public void setProductPriceId(Integer productPriceId) {
		this.productPriceId = productPriceId;
	}
	
	@XmlElement(name="productprice")
	public Integer getProductPriceValue() {
		return productPriceValue;
	}
	public void setProductPriceValue(Integer productPrice) {
		this.productPriceValue = productPrice;
	}
	@Override
	public String toString() {
		return "OrderLineBVO [orderLineId=" + orderLineId + ", productId="
				+ productId + ", productPriceId=" + productPriceId
				+ ", productPrice=" + productPriceValue + "]";
	}
}
