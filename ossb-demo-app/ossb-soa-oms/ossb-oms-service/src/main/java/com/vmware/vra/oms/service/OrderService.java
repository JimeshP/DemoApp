package com.vmware.vra.oms.service;

import javax.jms.JMSException;

import org.springframework.http.ResponseEntity;

import com.vmware.vra.oms.service.component.vo.ListOrderBVO;
import com.vmware.vra.oms.service.component.vo.OrderHeaderBVO;

public interface OrderService {

	ResponseEntity<ListOrderBVO> listOrders();
		
	ResponseEntity<OrderHeaderBVO> getOrder(Integer orderId);
	
	ResponseEntity<ListOrderBVO> getOrderListByCustomer(Integer customerId);
	
	ResponseEntity<OrderHeaderBVO> createOrder(OrderHeaderBVO orderBVO);
	
	ResponseEntity<OrderHeaderBVO> updateOrder(OrderHeaderBVO orderBVO);
	
	ResponseEntity<OrderHeaderBVO> deleteOrder(Integer orderId); 
	
	ResponseEntity<ListOrderBVO> processQueueOrder() throws JMSException;
	
}
 