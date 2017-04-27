package com.vmware.vra.oms.dao.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.vmware.vra.pms.dao.model.Product;
import com.vmware.vra.pms.dao.model.ProductPrice;

@Entity
@Table(name="order_line")
public class OrderLine {
	
	@Id
	@GeneratedValue
	@Column(name="ORDER_LINE_ID")
	private Integer orderLineId;
	
	@ManyToOne(targetEntity = OrderHeader.class, fetch = FetchType.EAGER)
	@JoinColumn(name="Order_Header_ORDER_ID",nullable=false)
	private OrderHeader orderHeader;
	
	@ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "Product_PROD_ID", nullable = false)
	private Product product; 
	
	@ManyToOne(targetEntity = ProductPrice.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "Product_Price_PRD_PRICE_ID", nullable = false)
	private ProductPrice productPrice;
	
	public OrderLine() {
		
	}
	
	public Integer getOrderLineId() {
		return orderLineId;
	}

	public void setOrderLineId(Integer orderLineId) {
		this.orderLineId = orderLineId;
	}

	public OrderHeader getOrderHeader() {
		return orderHeader;
	}

	public void setOrderHeader(OrderHeader orderHeader) {
		this.orderHeader = orderHeader;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductPrice getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(ProductPrice productPrice) {
		this.productPrice = productPrice;
	}

	/*@Override
	public String toString() {
		return "OrderLine [orderLineId=" + orderLineId + ", orderHeader="
				+ orderHeader + ", product=" + product + ", productPrice="
				+ productPrice + "]";
	}*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((orderLineId == null) ? 0 : orderLineId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderLine other = (OrderLine) obj;
		if (orderLineId == null) {
			if (other.orderLineId != null)
				return false;
		} else if (!orderLineId.equals(other.orderLineId))
			return false;
		return true;
	}
	
}
