package com.vmware.vra.oms.service.component.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="orderList")
public class ListOrderBVO extends StatusBVO { 
	
	private List<OrderHeaderBVO> orderList;

	
	@XmlElementWrapper(name="orders")
	@XmlElement(name="order")
	public List<OrderHeaderBVO> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderHeaderBVO> orderList) {
		this.orderList = orderList;
	} 

	
	//@Override
	public String toString() {
		return "ListOrderBVO [orderList=" + orderList + "]";
	}
	
}
