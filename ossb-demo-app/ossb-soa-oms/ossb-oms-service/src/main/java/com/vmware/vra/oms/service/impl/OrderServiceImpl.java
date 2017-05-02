package com.vmware.vra.oms.service.impl;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vmware.vra.oms.service.OrderService;
import com.vmware.vra.oms.service.component.BusOrderIFace;
import com.vmware.vra.oms.service.component.constants.ApplicationConstants.Message;
import com.vmware.vra.oms.service.component.exception.OMSServiceException;
import com.vmware.vra.oms.service.component.util.OrderProccessListner;
import com.vmware.vra.oms.service.component.vo.ListOrderBVO;
import com.vmware.vra.oms.service.component.vo.OrderHeaderBVO;
import com.vmware.vra.oms.service.component.vo.StatusBVO;
import com.vmware.vra.oms.service.constants.ApplicationConstants;
import com.vmware.vra.oms.service.constants.ApplicationConstants.ErrorCode;
import com.vmware.vra.oms.service.util.ServiceUtil;

@Controller
@RequestMapping(value = ApplicationConstants.OPERATOR_COMMAND_SLASH + ApplicationConstants.ORDER_SERVICE) 
public class OrderServiceImpl implements OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	BusOrderIFace busOrder;

	@Autowired
	private OrderProccessListner orderProccessListner;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ListOrderBVO> listOrders() { 
		if(logger.isDebugEnabled()) {
			logger.debug("Inside OrderServiceImpl.listOrders");
		}

		ListOrderBVO listOrderBVO = new ListOrderBVO(); 
		try {
			String message = "";
			listOrderBVO = busOrder.getOrderList();
			if(listOrderBVO == null) {
				listOrderBVO = new ListOrderBVO(); 
				message = Message.NO_DATA;
			}
			handleSuccessCase(listOrderBVO, message, null);

		} catch (Exception e) {
			OMSServiceException exp = null;
			if(e instanceof OMSServiceException) {
				exp = (OMSServiceException) e;
			} else {
				logger.error("Error in listOrders " + e.getMessage());
				exp = new OMSServiceException(ErrorCode.GET_ALL_ORDER_EXP_CODE, e.getMessage(), e);
			}
			handleException(listOrderBVO, exp);
		}
		return ServiceUtil.getResponseEntity(listOrderBVO); 
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, 
	value = ApplicationConstants.OPERATOR_COMMAND_SLASH 
	+ ApplicationConstants.URI_PATH_VARIABLE_START + ApplicationConstants.ORDER_ID + ApplicationConstants.URI_PATH_VARIABLE_END) 
	public @ResponseBody ResponseEntity<OrderHeaderBVO> getOrder(@PathVariable Integer orderId) {
		if(logger.isDebugEnabled()) {
			logger.debug("Inside OrderServiceImpl.getOrder " + orderId);
		} 

		OrderHeaderBVO orderHeaderBVO = null;
		try {
			orderHeaderBVO = busOrder.getOrder(orderId);
			String message = "";
			if(orderHeaderBVO == null) {
				orderHeaderBVO = new OrderHeaderBVO();
				message = Message.NO_DATA_FOR_ORDER;
			} 
			handleSuccessCase(orderHeaderBVO, message, orderId);

		} catch (Exception e) {
			orderHeaderBVO = new OrderHeaderBVO();
			OMSServiceException exp = null;
			if(e instanceof OMSServiceException) {
				exp = (OMSServiceException) e;
			} else {
				logger.error("Error in getOrder " + e.getMessage());
				exp = new OMSServiceException(ErrorCode.GET_ORDER_EXP_CODE, e.getMessage(), e);
			}
			handleException(orderHeaderBVO, exp);

		} 
		return ServiceUtil.getResponseEntity(orderHeaderBVO); 
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, 
	value = ApplicationConstants.OPERATOR_COMMAND_SLASH 
	+ ApplicationConstants.CUSTOMER 
	+ ApplicationConstants.OPERATOR_COMMAND_SLASH 
	+ ApplicationConstants.URI_PATH_VARIABLE_START + ApplicationConstants.CUSTOMER_ID + ApplicationConstants.URI_PATH_VARIABLE_END) 
	public @ResponseBody ResponseEntity<ListOrderBVO> getOrderListByCustomer(@PathVariable Integer customerId) {
		if(logger.isDebugEnabled()) {
			logger.debug("Inside OrderServiceImpl.getOrderListByCustomer " + customerId);
		} 

		ListOrderBVO listOrderBVO = new ListOrderBVO();
		try {
			listOrderBVO = busOrder.getOrderListByCustomer(customerId);
			String msg = "";
			if(listOrderBVO == null 
					|| listOrderBVO.getOrderList() == null || 
					listOrderBVO.getOrderList().isEmpty()) {
				listOrderBVO = new ListOrderBVO();
				msg = Message.NO_ORDERS_FOR_CUSTOMER;
			} 
			handleSuccessCase(listOrderBVO, msg, customerId);

		} catch (Exception e) {
			OMSServiceException exp = null;
			if(e instanceof OMSServiceException) {
				exp = (OMSServiceException) e;
			} else {
				logger.error("Error in getOrderListByCustomer " + e.getMessage());
				exp = new OMSServiceException(ErrorCode.GET_ALL_ORDER_PER_CUST_EXP_CODE, e.getMessage(), e);
			}
			handleException(listOrderBVO, exp);
		}
		return ServiceUtil.getResponseEntity(listOrderBVO); 
	}

	@Override
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<OrderHeaderBVO> createOrder(@RequestBody OrderHeaderBVO orderBVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("Inside OrderServiceImpl.createOrder " + orderBVO);
		} 

		return createOrUpdateOrder(orderBVO, com.vmware.vra.oms.service.component.constants.ApplicationConstants.OPERATION_TYPE_CREATE); 
	}

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<OrderHeaderBVO> updateOrder(@RequestBody OrderHeaderBVO orderBVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("Inside OrderServiceImpl.updateOrder " + orderBVO);
		} 
		return createOrUpdateOrder(orderBVO, com.vmware.vra.oms.service.component.constants.ApplicationConstants.OPERATION_TYPE_UPDATE); 
	}

	@Override
	@RequestMapping(method = RequestMethod.DELETE,
	value = ApplicationConstants.OPERATOR_COMMAND_SLASH 
	+ ApplicationConstants.URI_PATH_VARIABLE_START + ApplicationConstants.ORDER_ID + ApplicationConstants.URI_PATH_VARIABLE_END) 
	public @ResponseBody ResponseEntity<OrderHeaderBVO> deleteOrder(@PathVariable Integer orderId) {
		if(logger.isDebugEnabled()) {
			logger.debug("Inside OrderServiceImpl.deleteOrder " + orderId);
		} 

		OrderHeaderBVO retVal = new OrderHeaderBVO();
		try {
			Boolean	status = busOrder.deleteOrder(orderId);
			String msg = "";
			if(status) {
				msg = Message.ORDER_DELETE_SUCCESS;
			} else {
				msg = Message.ORDER_DELETE_FAILURE;
			}
			handleSuccessCase(retVal, msg, orderId);
		} catch (Exception e) {
			OMSServiceException exp = null;
			if(e instanceof OMSServiceException) {
				exp = (OMSServiceException) e;
			} else {
				logger.error("Error in deleteOrder " + e.getMessage());
				exp = new OMSServiceException(ErrorCode.GET_DELETE_ORDER_EXP_CODE, e.getMessage(), e);
			}
			handleException(retVal, exp);
		}
		return ServiceUtil.getResponseEntity(retVal); 
	}

	private ResponseEntity<OrderHeaderBVO> createOrUpdateOrder(
			OrderHeaderBVO orderBVO, String operationType) { 
		OrderHeaderBVO retVal = new OrderHeaderBVO(); 
		try {
			retVal = busOrder.createOrUpdateOrder(orderBVO, operationType);
			String msg = "";
			if(retVal == null) {
				retVal = new OrderHeaderBVO();
				msg = Message.NO_ORDERS_FOR_CUSTOMER;
			} 
			handleSuccessCase(retVal, msg, null);
		} catch (Exception e) {
			OMSServiceException exp = null;
			if(e instanceof OMSServiceException) {
				exp = (OMSServiceException) e;
			} else {
				logger.error("Error in createOrUpdateOrder " + e.getMessage());
				exp = new OMSServiceException(ErrorCode.GET_SAVE_UPDATE_ORD_EXP_CODE, e.getMessage(), e);
			}
			handleException(retVal, exp);
		}
		return ServiceUtil.getResponseEntity(retVal);
	}

	private void handleException(StatusBVO statusBVO, OMSServiceException e) { 
		statusBVO.setErrorCode(e.getErrorCode());
		statusBVO.setMessage(e.getExceptionMessage());
		statusBVO.setStatus("Failure");
	}

	private void handleSuccessCase(StatusBVO statusBVO, String message, Integer id) {
		statusBVO.setErrorCode("");

		if(message != null && message != "") {
			String msg = message;
			msg = (id == null ? msg : msg + id);
			statusBVO.setMessage(msg);
		}

		statusBVO.setStatus("Success");

	}

	@Override
	@RequestMapping(method = RequestMethod.POST, 
	value = ApplicationConstants.OPERATOR_COMMAND_SLASH 
	+ ApplicationConstants.PROCESS_QUEUE_ORDER) 
	public ResponseEntity<ListOrderBVO> processQueueOrder() throws JMSException {
		ListOrderBVO listOrderBVO=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		listOrderBVO=orderProccessListner.onMessage();
		return new ResponseEntity(listOrderBVO, responseHeaders, HttpStatus.OK);
	}

}
