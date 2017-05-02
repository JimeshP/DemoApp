/**
 * 
 */
package com.vmware.vra.oms.dao.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vra.oms.dao.exception.DAOException;
import com.vmware.vra.oms.dao.model.OrderHeader;


public interface OrderDaoIFace {

	public List<OrderHeader> getAllOrders() throws DAOException;
	
	public OrderHeader getOrderByOrderId(Integer orderId) throws DAOException;
	
	public Set<OrderHeader> getOrderListByCustomer(Integer custId) throws DAOException;
	
	public OrderHeader saveOrRpdateOrder(OrderHeader orderHeader) throws DAOException;

	public OrderHeader deleteOrder(Integer orderId) throws DAOException;
}
