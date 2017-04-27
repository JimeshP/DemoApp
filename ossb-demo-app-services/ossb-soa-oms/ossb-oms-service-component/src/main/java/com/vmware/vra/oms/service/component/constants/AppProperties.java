/**
 * 
 */
package com.vmware.vra.oms.service.component.constants;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author rams
 *
 */
@Component
public class AppProperties implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1417068555976713898L;
	
	@Value(value="${OMS.BROKER.URL}")
	public String activeMq;
	
	@Value(value="${OMS.BROKER.USERNAME}")
	public String userName;
	
	@Value(value="${OMS.BROKER.PASSWORD}")
	public String password;
	
	@Value(value="${OMS.QUEUE.NAME}")
	public String queue;
	
	
	

}
