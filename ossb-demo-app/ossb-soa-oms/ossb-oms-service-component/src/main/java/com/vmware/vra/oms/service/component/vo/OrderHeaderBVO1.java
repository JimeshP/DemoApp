package com.vmware.vra.oms.service.component.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class OrderHeaderBVO1 implements Serializable {
	
	private StatusBVO statusBVO;
	private OrderHeaderBVO orderHeaderBVO;

	@XmlElement(name="status")
	public StatusBVO getStatusBVO() {
		return statusBVO;
	}

	public void setStatusBVO(StatusBVO statusBVO) {
		this.statusBVO = statusBVO;
	}
	
	@XmlElement(name="order")
	public OrderHeaderBVO getOrderHeaderBVO() {
		return orderHeaderBVO;
	}

	public void setOrderHeaderBVO(OrderHeaderBVO orderHeaderBVO) {
		this.orderHeaderBVO = orderHeaderBVO;
	}

	@Override
	public String toString() {
		return "OrderHeaderBVO1 [statusBVO=" + statusBVO + ", orderHeaderBVO="
				+ orderHeaderBVO + "]";
	}
	
	

}
