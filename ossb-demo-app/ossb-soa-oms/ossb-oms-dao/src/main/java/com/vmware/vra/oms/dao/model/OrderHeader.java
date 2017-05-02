package com.vmware.vra.oms.dao.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.vmware.vra.cms.dao.model.Customer;

@Entity
@Table(name="order_header")
public class OrderHeader {
	
	@Id
	@GeneratedValue
	@Column(name="ORDER_ID")
	private Integer orderId;
	
	@ManyToOne(targetEntity = Customer.class, fetch = FetchType.EAGER)
	@JoinColumn(name="Customer_CUST_ID", nullable = false)
	private Customer customer;
	
	@Column(name="ORDER_DATE", nullable = false)
	private Date orderDate;	
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="orderHeader", fetch = FetchType.EAGER) //, targetEntity=OrderLine.class, fetch = FetchType.EAGER)
	private List<OrderLine> orderLineList;
	
	public OrderHeader() {

	}
	
	@Column(name="STATUS_ID",nullable=false)
	private Integer statusId;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public List<OrderLine> getOrderLineList() {
		return orderLineList;
	}

	public void setOrderLineList(List<OrderLine> orderLineList) {
		this.orderLineList = orderLineList;
	}

	@Override
	public String toString() {
		Object aa = orderLineList == null ? "" : orderLineList; 
		return "OrderHeader [orderId=" + orderId + ", customer=" + customer
				+ ", orderLineList=" + aa + ", statusId=" + statusId
				+ "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
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
		OrderHeader other = (OrderHeader) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		return true;
	}

}
