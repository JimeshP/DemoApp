/**
 * 
 */
package com.vmware.vra.cms.service.component;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vra.cms.service.component.vo.CreateCustomerResponse;
import com.vmware.vra.cms.service.component.vo.CustomerBVO;
import com.vmware.vra.cms.service.component.vo.DeleteCustomerResponse;
import com.vmware.vra.cms.service.component.vo.ListCustomerBVO;
import com.vmware.vra.cms.service.component.vo.UpdateCustomerResponse;

/**
 * @author RamS
 *
 */
@Component
public interface BusCustomerIFace {

	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public ListCustomerBVO getCustomer(Integer customer);
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public ListCustomerBVO listCustomers();
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public CreateCustomerResponse addCustomer(CustomerBVO customerBVO);

	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public UpdateCustomerResponse updateCustomer(CustomerBVO customerBVO);

	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public DeleteCustomerResponse deleteCustomer(Integer custId);
	
}
