/**
 * 
 */
package com.vmware.vra.oms.service.component.util;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vmware.vra.cms.dao.model.Customer;
import com.vmware.vra.oms.dao.model.OrderHeader;
import com.vmware.vra.oms.dao.model.OrderLine;
import com.vmware.vra.oms.service.component.constants.ApplicationConstants;
import com.vmware.vra.oms.service.component.vo.ListOrderBVO;
import com.vmware.vra.oms.service.component.vo.OrderHeaderBVO;
import com.vmware.vra.oms.service.component.vo.OrderLineBVO;
import com.vmware.vra.pms.dao.model.Product;
import com.vmware.vra.pms.dao.model.ProductPrice;


public class OrderUtil {

	private static final Logger logger = LoggerFactory.getLogger(OrderUtil.class);
	
	public static ListOrderBVO getListOrderBVO(Collection<OrderHeader> orderList) { 
		ListOrderBVO listOrderBVO = new ListOrderBVO();
		List<OrderHeaderBVO> orderBVOList = new ArrayList<OrderHeaderBVO>();
		for(OrderHeader order : orderList) {
			OrderHeaderBVO orderBvo = getOrderBVO(order);
			orderBVOList.add(orderBvo);
		}
		listOrderBVO.setOrderList(orderBVOList);
		return listOrderBVO;
	}

	public static OrderHeaderBVO getOrderBVO(OrderHeader order) {
		OrderHeaderBVO retVal = null;
		if(order != null) {
			retVal = new OrderHeaderBVO();
			
			if(order.getCustomer() != null) {
				retVal.setCustomerId(order.getCustomer().getCustomerId());
				retVal.setCustomerName(order.getCustomer().getCustomerFirstName()+" "+order.getCustomer().getCustomerLastName());
			}
			
			retVal.setOrderId(order.getOrderId());
			retVal.setStatusId(order.getStatusId()); 
			retVal.setOrderDate(order.getOrderDate());
			if(order.getOrderLineList() != null) {
				List<OrderLineBVO> orderLineBVOLIst = new ArrayList<OrderLineBVO>();
				for(OrderLine orderLine : order.getOrderLineList()) {
					OrderLineBVO orderLineBVO = new OrderLineBVO();
					orderLineBVO.setOrderLineId(orderLine.getOrderLineId());
					//orderLineBVO.setOrderHeaderId(order.getOrderId());
					orderLineBVO.setProductId(orderLine.getProduct().getProductId());
					orderLineBVO.setProductPriceId(orderLine.getProductPrice().getProductPriceId());
					orderLineBVO.setProductPriceValue(orderLine.getProductPrice().getPricePerUnit());
					orderLineBVOLIst.add(orderLineBVO);
				}
				
				retVal.setOrderLineBVOList(orderLineBVOLIst); 	
			}
		}
		return retVal;
	}

	public static OrderHeader getOrderHeader(OrderHeaderBVO orderBVO, String operationType) { 
		OrderHeader orderHeader = null;
		if(orderBVO != null) {
			orderHeader = new OrderHeader();
			if(orderBVO.getOrderId() != null 
					&& ApplicationConstants.OPERATION_TYPE_UPDATE.equalsIgnoreCase(operationType)) {
				orderHeader.setOrderId(orderBVO.getOrderId());
			}
			Customer customer = new Customer();
			customer.setCustomerId(orderBVO.getCustomerId());
			orderHeader.setCustomer(customer); 
			orderHeader.setStatusId(orderBVO.getStatusId());
			orderHeader.setOrderDate(new Date());
			
			List<OrderLine> orderLineList = new ArrayList<OrderLine>();
			List<OrderLineBVO> orderLineBVOList = orderBVO.getOrderLineBVOList();
			if(orderLineBVOList != null) {
				for(OrderLineBVO orderLineBVO : orderLineBVOList) {
					OrderLine orderLine = new OrderLine();
					
					if(orderLineBVO.getOrderLineId() != null
							&& ApplicationConstants.OPERATION_TYPE_UPDATE.equalsIgnoreCase(operationType)) {
						orderLine.setOrderLineId(orderLineBVO.getOrderLineId());
					}
					/*if(orderLineBVO.getOrderHeaderId() != null) {
						orderLine.setOrderHeader(orderHeader);
					}*/
					if(orderLineBVO.getProductId() != null) {
						Product product = new Product();
						product.setProductId(orderLineBVO.getProductId());
						orderLine.setProduct(product);
					}
					if(orderLineBVO.getProductPriceId() != null) {
						ProductPrice pp = new ProductPrice();
						pp.setProductPriceId(orderLineBVO.getProductPriceId());
						orderLine.setProductPrice(pp);
					}
					orderLineList.add(orderLine);
				}
				orderHeader.setOrderLineList(orderLineList); 
			}
			
		}
		return orderHeader;
	}
	
	public static String getOrderRequest(OrderHeaderBVO orderBVO) {
		StringWriter sw = new StringWriter();
		try {
			JAXBContext jContext = JAXBContext.newInstance(OrderHeaderBVO.class);
			Marshaller marshaller1 = jContext.createMarshaller();
			marshaller1.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller1.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			marshaller1.marshal(orderBVO, sw);

		} catch (JAXBException e) {
			logger.error(e.getMessage());
		}
		return sw.toString();
	}
}
