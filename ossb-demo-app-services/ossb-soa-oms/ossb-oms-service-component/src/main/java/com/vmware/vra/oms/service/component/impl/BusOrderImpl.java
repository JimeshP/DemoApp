package com.vmware.vra.oms.service.component.impl;

import java.util.List;
import java.util.Set;

import javax.jms.Destination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vra.oms.dao.constants.ApplicationConstants;
import com.vmware.vra.oms.dao.exception.DAOException;
import com.vmware.vra.oms.dao.model.OrderHeader;
import com.vmware.vra.oms.dao.repositories.OrderDaoIFace;
import com.vmware.vra.oms.service.component.BusOrderIFace;
import com.vmware.vra.oms.service.component.constants.ApplicationConstants.ErrorCode;
import com.vmware.vra.oms.service.component.constants.ApplicationConstants.ErrorMessage;
import com.vmware.vra.oms.service.component.exception.OMSServiceException;
import com.vmware.vra.oms.service.component.util.JMSClient;
import com.vmware.vra.oms.service.component.util.OrderUtil;
import com.vmware.vra.oms.service.component.vo.ListOrderBVO;
import com.vmware.vra.oms.service.component.vo.OrderHeaderBVO;


@Component    
public class BusOrderImpl implements BusOrderIFace {

	private static final Logger logger = LoggerFactory.getLogger(BusOrderImpl.class);
	@Autowired
	private OrderDaoIFace orderDao;
	
	@Autowired
	private JMSClient jmsClient;
	
	@Autowired
	@Qualifier("omsDestination")
	private Destination destination;
	
	
	
	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public ListOrderBVO getOrderList() throws OMSServiceException { 
		if(logger.isDebugEnabled()) {
			logger.debug("Inside getOrderList");
		}
		ListOrderBVO retVal = null;
		try {
			List<OrderHeader> orderList = orderDao.getAllOrders();
			retVal = OrderUtil.getListOrderBVO(orderList);
			if(logger.isDebugEnabled()) {
				logger.debug("RetVal getOrderList " + retVal);
			}
		} catch (DAOException e) {
			throw new OMSServiceException(e.getErrorCode(), e.getExceptionMessage(), e.getThrowable());
		} catch(Exception e) {
			logger.error("Exception in getOrderList " + e.getMessage());
			throw new OMSServiceException(ErrorCode.GET_ALL_ORDER_EXP_CODE, ErrorMessage.GENERAL_EXP_MSG, e);
		}
		
		return retVal;
	}



	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public OrderHeaderBVO getOrder(Integer orderId) throws OMSServiceException { 
		if(logger.isDebugEnabled()) {
			logger.debug("Inside getOrder " + orderId);
		}
		OrderHeaderBVO orderHeaderBVO = null;
		try {
			OrderHeader orderH = orderDao.getOrderByOrderId(orderId);
			orderHeaderBVO = OrderUtil.getOrderBVO(orderH);
			if(logger.isDebugEnabled()) {
				logger.debug("RetVal getOrder " + orderHeaderBVO);
			}
		} catch (DAOException e) {
			throw new OMSServiceException(e.getErrorCode(), e.getExceptionMessage(), e.getThrowable());
		} catch(Exception e) {
			logger.error("Exception in getOrder " + e.getMessage());
			throw new OMSServiceException(ErrorCode.GET_ORDER_EXP_CODE, ErrorMessage.GENERAL_EXP_MSG, e);
		}
		
		return orderHeaderBVO;
	}



	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public ListOrderBVO getOrderListByCustomer(Integer customerId) throws OMSServiceException { 
		if(logger.isDebugEnabled()) {
			logger.debug("Inside getOrderListByCustomer " + customerId);
		}
		ListOrderBVO retVal = null;
		try {
			Set<OrderHeader> orderHeaderList = orderDao.getOrderListByCustomer(customerId);
			retVal = OrderUtil.getListOrderBVO(orderHeaderList);
			if(logger.isDebugEnabled()) {
				logger.debug("RetVal getOrderListByCustomer " + retVal);
			}
		} catch (DAOException e) {
			throw new OMSServiceException(e.getErrorCode(), e.getExceptionMessage(), e.getThrowable());
		} catch(Exception e) {
			logger.error("Exception in getOrderListByCustomer " + e.getMessage());
			throw new OMSServiceException(ErrorCode.GET_ALL_ORDER_PER_CUST_EXP_CODE, ErrorMessage.GENERAL_EXP_MSG, e); 
		}
		return retVal;
	}



	@Override
	@Transactional(rollbackFor=OMSServiceException.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public OrderHeaderBVO createOrUpdateOrder(OrderHeaderBVO orderBVO, String operationType) throws OMSServiceException {
		if(logger.isDebugEnabled()) {
			logger.debug("Inside createOrUpdateOrder " + orderBVO);
		}
		OrderHeader orderHeader = null;
		try {
			orderHeader = OrderUtil.getOrderHeader(orderBVO, operationType);
			orderHeader = orderDao.saveOrRpdateOrder(orderHeader);
			orderBVO = OrderUtil.getOrderBVO(orderHeader);
			String orderRequest=OrderUtil.getOrderRequest(orderBVO);
			
			if(operationType.equalsIgnoreCase(com.vmware.vra.oms.service.component.constants.ApplicationConstants.OPERATION_TYPE_CREATE)) {
				jmsClient.sendData(orderRequest, destination);
			}
			
			
			if(logger.isDebugEnabled()) {
				logger.debug("RetVal createOrUpdateOrder " + orderHeader);
			}
		} catch (DAOException e) {
			throw new OMSServiceException(e.getErrorCode(), e.getExceptionMessage(), e.getThrowable());
		} catch(DataIntegrityViolationException ex) {
			
		}catch(Exception e) {
			logger.error("Exception in createOrUpdateOrder " + e.getMessage());
			throw new OMSServiceException(ErrorCode.GET_SAVE_UPDATE_ORD_EXP_CODE, ErrorMessage.GENERAL_EXP_MSG, e);
		}
		return orderBVO;
	}



	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public Boolean deleteOrder(Integer orderId) throws OMSServiceException {  
		if(logger.isDebugEnabled()) {
			logger.debug("Inside deleteOrder " + orderId);
		}
		try {
			orderDao.deleteOrder(orderId);
		} catch (DAOException e) {
			throw new OMSServiceException(e.getErrorCode(), e.getExceptionMessage(), e.getThrowable());
		} catch(Exception e) {
			logger.error("Exception in deleteOrder " + e.getMessage());
			throw new OMSServiceException(ErrorCode.GET_DELETE_ORDER_EXP_CODE, ErrorMessage.GENERAL_EXP_MSG, e);
		}
		return true;
	}
	
}
