/**
 * 
 */
package com.vmware.vra.cms.dao.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vra.cms.dao.model.Address;

/**
 * @author RamS
 *
 */

public interface AddressDaoIFace {
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=false)
	public Address saveorupdate(Address address)throws DataAccessException;
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=true)
	public List<Address> listAddress()throws DataAccessException;
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=true)
	public List<Address> listAddressByCustId(Integer custId)throws DataAccessException;
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly = true)
	public List<Address> listAddressByAddressId(Integer addressId) throws DataAccessException;

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteAddress(Address address)throws DataAccessException;

}
