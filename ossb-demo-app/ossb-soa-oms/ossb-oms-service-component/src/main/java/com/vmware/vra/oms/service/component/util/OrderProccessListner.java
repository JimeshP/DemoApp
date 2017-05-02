/**
 * 
 */
package com.vmware.vra.oms.service.component.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vmware.vra.oms.service.component.constants.AppProperties;
import com.vmware.vra.oms.service.component.constants.ApplicationConstants;
import com.vmware.vra.oms.service.component.impl.BusOrderImpl;
import com.vmware.vra.oms.service.component.vo.ListOrderBVO;
import com.vmware.vra.oms.service.component.vo.OrderHeaderBVO;

/**
 * @author rams
 * @param <M>
 *
 */
@Component
public class OrderProccessListner {

	private static final Logger logger = LoggerFactory.getLogger(OrderUtil.class);

	@Autowired
	private BusOrderImpl busOrderImpl;
	
	@Autowired
	private AppProperties props;



	public ListOrderBVO onMessage() throws JMSException{

		ListOrderBVO listOrderBVO=new ListOrderBVO();

		try {
			logger.debug("Activmq={}",props.activeMq);
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(props.userName,props.password,props.activeMq);
			Destination destination = new ActiveMQQueue(props.queue);

			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageConsumer consumer = session.createConsumer(destination);
			List<OrderHeaderBVO> orderList=new ArrayList<OrderHeaderBVO>();
			for (;;) {
				logger.debug("Waiting for message.");
				Message message = consumer.receive(10000);
				if (message == null) {
					logger.debug("no message.");
					break;
				}else{
					ActiveMQMessage msg = (ActiveMQMessage) message;
					ActiveMQTextMessage tMsg = (ActiveMQTextMessage) msg;

					InputStream xmlInputStream = new ByteArrayInputStream(tMsg.getText().getBytes("UTF-8"));
					JAXBContext orderHeaderJAXBContext = JAXBContext.newInstance(OrderHeaderBVO.class);
					Unmarshaller orderHeaderReqUnmarshaller = orderHeaderJAXBContext.createUnmarshaller();

					OrderHeaderBVO orderHeaderBVO = (OrderHeaderBVO) orderHeaderReqUnmarshaller.unmarshal(xmlInputStream);
					orderHeaderBVO.setStatusId(1);
					busOrderImpl.createOrUpdateOrder(orderHeaderBVO, ApplicationConstants.OPERATION_TYPE_UPDATE);

					logger.debug("Got message: " + message);
					logger.debug("Got orderHeaderBVO: " + orderHeaderBVO.toString());

					orderList.add(orderHeaderBVO);
				}
			}
			connection.close();
			listOrderBVO.setOrderList(orderList);
			listOrderBVO.setStatus("success");

		} catch (Exception ex) {
			logger.error("Exception occured={}",ex);
		}

		return listOrderBVO; 
	}

}
