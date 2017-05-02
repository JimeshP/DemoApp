/**
 * 
 */
package com.vmware.vra.oms.service.component.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement(name = "order")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderHeaderBVO extends StatusBVO {

	private Integer orderId;
	private Integer customerId;
	private String customerName;
	private Integer statusId;
	private Date orderDate;
	private List<OrderLineBVO> orderLineBVOList = new ArrayList<OrderLineBVO>();
	
	@XmlElement(name="orderid")
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	@XmlElement(name="customerid")
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	@XmlElement(name="customername")
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@XmlElement(name="statusid")
	public Integer getStatusId() {
		return statusId;
	}
	
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	
	@XmlElement(name="orderdate")
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	@XmlElementWrapper(name="orderLines")
	@XmlElement(name="orderLine")
	public List<OrderLineBVO> getOrderLineBVOList() {
		return orderLineBVOList;
	}
	public void setOrderLineBVOList(List<OrderLineBVO> orderLineBVOList) {
		this.orderLineBVOList = orderLineBVOList;
	}
	@Override
	public String toString() {
		return "OrderHeaderBVO [orderId=" + orderId + ", customerId="
				+ customerId + ", customerName=" + customerName + ", statusId="
				+ statusId + ", orderDate=" + orderDate + ", orderLineBVOList="
				+ orderLineBVOList + "]";
	}
	
}
