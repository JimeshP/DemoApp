/**
 * 
 */
package com.vmware.vra.oms.service.component.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;


import org.springframework.jms.core.MessagePostProcessor;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

/**
 * @author rams
 *
 */


public class JMSClient {


	private static final Logger logger = LoggerFactory.getLogger(JMSClient.class);

	@Autowired
	@Qualifier("jmsTemplate")
	private JmsTemplate jmsTemplate;


	/**
	 * send text to default destination
	 * @param text
	 */
	public void sendData(String orderData, Destination destination) {
		logger.debug(">> Entering sendData(String, Destination) with orderData = {}", orderData, destination);
		jmsTemplate.convertAndSend(destination, orderData, new MessagePostProcessor() {
			@Override
			public Message postProcessMessage(Message orderData)
					throws JMSException {
				logger.debug(">>EXITING {} with Message {} ", this.getClass(), orderData);
				return orderData;
			}
		});
		logger.debug("Data Published to order queue");
	}
	
	
	


}