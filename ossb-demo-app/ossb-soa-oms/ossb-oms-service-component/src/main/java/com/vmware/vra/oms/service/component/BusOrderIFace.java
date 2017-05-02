/**
 * 
 */
package com.vmware.vra.oms.service.component;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vra.oms.dao.model.OrderHeader;
import com.vmware.vra.oms.service.component.exception.OMSServiceException;
import com.vmware.vra.oms.service.component.vo.ListOrderBVO;
import com.vmware.vra.oms.service.component.vo.OrderHeaderBVO;


public interface BusOrderIFace {
	
	ListOrderBVO getOrderList() throws OMSServiceException;

	OrderHeaderBVO getOrder(Integer orderId) throws OMSServiceException;
	
	ListOrderBVO getOrderListByCustomer(Integer customerId) throws OMSServiceException;
	
	OrderHeaderBVO createOrUpdateOrder(OrderHeaderBVO orderBVO, String operationType) throws OMSServiceException;
	
	Boolean deleteOrder(Integer orderId) throws OMSServiceException; 
}
