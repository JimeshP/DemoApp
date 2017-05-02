/**
 * 
 */
package com.vmware.vra.oms.dao.repositories.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vra.cms.dao.model.Customer;
import com.vmware.vra.oms.dao.constants.ApplicationConstants.ErrorCode;
import com.vmware.vra.oms.dao.constants.ApplicationConstants.ErrorMessage;
import com.vmware.vra.oms.dao.exception.DAOException;
import com.vmware.vra.oms.dao.model.OrderHeader;
import com.vmware.vra.oms.dao.model.OrderLine;
import com.vmware.vra.oms.dao.repositories.OrderDaoIFace;

@Repository
public class OrderDaoImpl extends HibernateDaoSupport implements OrderDaoIFace {
	
	private final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);

	@Autowired
	public void setMySessionFactory(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor=DAOException.class,propagation=Propagation.REQUIRED,readOnly=true)
	public List<OrderHeader> getAllOrders() throws DAOException {
		if(logger.isDebugEnabled()) {
			logger.debug("Inside getAllOrders");
		}
		List<OrderHeader> orderList=null;
		try {
			orderList=this.getHibernateTemplate().loadAll(OrderHeader.class);
		}catch(DataAccessException ex) {
			logger.error("DataAccessException in GetAllOrders " + ex.getMessage());
			throw new DAOException(ErrorCode.GET_ALL_ORDER_EXP_CODE, ErrorMessage.DATAACCESSEXP_MSG, ex);
		}catch(Exception ex) {
			logger.error("Exception in GetAllOrders " + ex.getMessage());
			throw new DAOException(ErrorCode.GENERAL_EXP_CODE, ErrorMessage.GENERAL_EXP_MSG, ex);
		}
		
		return orderList;
	}

	@Transactional(rollbackFor=DAOException.class,propagation=Propagation.REQUIRED,readOnly=true)
	public OrderHeader getOrderByOrderId(Integer orderId) throws DAOException {
		if(logger.isDebugEnabled()) {
			logger.debug("Inside getOrderByOrderId " + orderId);
		}
		OrderHeader retVal = null;
		try {
			retVal = this.getHibernateTemplate().get(OrderHeader.class, orderId);
		}catch(DataAccessException ex) {
			logger.error("DataAccessException in getOrderByOrderId " + ex.getMessage());
			throw new DAOException(ErrorCode.GET_ORDER_EXP_CODE, ErrorMessage.DATAACCESSEXP_MSG, ex);
		}catch(Exception ex) {
			logger.error("Exception in getOrderByOrderId " + ex.getMessage());
			throw new DAOException(ErrorCode.GENERAL_EXP_CODE, ErrorMessage.GENERAL_EXP_MSG, ex);
		}
		
		return retVal;
	}

	@Transactional(rollbackFor=DAOException.class,propagation=Propagation.REQUIRED,readOnly=true)
	public Set<OrderHeader> getOrderListByCustomer(Integer custId)	throws DAOException {
		if(logger.isDebugEnabled()) {
			logger.debug("Inside getOrderListByCustomer " + custId);
		}
		
		Set<OrderHeader> retVal = null;
		try {
			DetachedCriteria pagingCriteria = DetachedCriteria.forClass(OrderHeader.class);
			if(custId!=null){
				Customer customer = new Customer();
				customer.setCustomerId(custId);
				
				pagingCriteria.add(Restrictions.eq("customer", customer));
			}
			List<OrderHeader> orderList= (List<OrderHeader>) this.getHibernateTemplate().findByCriteria(pagingCriteria);
			retVal = new HashSet<OrderHeader>();
			retVal.addAll(orderList);
			
		}catch(DataAccessException ex) {
			logger.error("DataAccessException in getOrderListByCustomer " + ex.getMessage());
			throw new DAOException(ErrorCode.GET_ALL_ORDER_PER_CUST_EXP_CODE, ErrorMessage.DATAACCESSEXP_MSG, ex);
		}catch(Exception ex) {
			logger.error("Exception in getOrderListByCustomer " + ex.getMessage());
			throw new DAOException(ErrorCode.GENERAL_EXP_CODE, ErrorMessage.GENERAL_EXP_MSG, ex);
		}
		

		return retVal;
	}

	@Transactional(rollbackFor=DAOException.class,propagation=Propagation.REQUIRED,readOnly=false)
	public OrderHeader saveOrRpdateOrder(OrderHeader orderHeader)
			throws DAOException {
		if(logger.isDebugEnabled()) {
			logger.debug("Inside saveOrRpdateOrder " + orderHeader);
		}
		
		try { 
			List<OrderLine> orderLineList = new ArrayList<OrderLine>(orderHeader.getOrderLineList());
			orderHeader.getOrderLineList().clear();

			//First we need to save the orderHeader, so that we can get the orderHeaderID, which is required to save the order line
			//this.getHibernateTemplate().saveOrUpdate(orderHeader);
			
			for(OrderLine orderLine : orderLineList) {
				orderLine.setOrderHeader(orderHeader); 
			}
			orderHeader.setOrderLineList(orderLineList); 
			
			this.getHibernateTemplate().saveOrUpdate(orderHeader);
			
			OrderHeader oh = getOrderByOrderId(orderHeader.getOrderId());
			
		}catch(DataAccessException ex) {
			//ex.printStackTrace();
			logger.error("DataAccessException in saveOrRpdateOrder " + ex.getMessage());
			throw new DAOException(ErrorCode.GET_SAVE_UPDATE_ORD_EXP_CODE, ErrorMessage.DATAACCESSEXP_MSG, ex);
		}catch(Exception ex) {
			//ex.printStackTrace();
			logger.error("Exception in saveOrRpdateOrder " + ex.getMessage());
			throw new DAOException(ErrorCode.GENERAL_EXP_CODE, ErrorMessage.GENERAL_EXP_MSG, ex);
		}
		
		
		return orderHeader;
	}

	@Transactional(rollbackFor=DAOException.class,propagation=Propagation.REQUIRED,readOnly=false)
	public OrderHeader deleteOrder(Integer orderId) throws DAOException {
		if(logger.isDebugEnabled()) {
			logger.debug("Inside deleteOrder " + orderId);
		}
		OrderHeader orderHeader = null;
		try {
			orderHeader = this.getHibernateTemplate().get(OrderHeader.class, orderId);
			this.getHibernateTemplate().delete(orderHeader);
		}catch(DataAccessException ex) {
			logger.error("DataAccessException in deleteOrder " + ex.getMessage());
			throw new DAOException(ErrorCode.GET_DELETE_ORDER_EXP_CODE, ErrorMessage.DATAACCESSEXP_MSG, ex);
		}catch(Exception ex) {
			logger.error("Exception in deleteOrder " + ex.getMessage());
			throw new DAOException(ErrorCode.GENERAL_EXP_CODE, ErrorMessage.GENERAL_EXP_MSG, ex);
		}
		return orderHeader;
	}
	
}
