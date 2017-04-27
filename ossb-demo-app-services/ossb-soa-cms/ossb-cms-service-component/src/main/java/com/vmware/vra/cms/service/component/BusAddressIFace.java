/**
 * 
 */
package com.vmware.vra.cms.service.component;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vra.cms.service.component.vo.AddressBVO;
import com.vmware.vra.cms.service.component.vo.CreateAddressResponse;
import com.vmware.vra.cms.service.component.vo.DeleteAddressResponse;
import com.vmware.vra.cms.service.component.vo.ListAddressResponse;

/**
 * @author RamS
 *
 */
@Component
public interface BusAddressIFace {
	
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public ListAddressResponse listAddress() throws Exception;
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public CreateAddressResponse addAddress(AddressBVO addressBVO)throws Exception;
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public ListAddressResponse listAddressByCustomerId(Integer custId) throws Exception;
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public ListAddressResponse listAddressByAddressId(Integer addressId) throws Exception;

	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public DeleteAddressResponse deleteAddress(Integer addressId);

}
