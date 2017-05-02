/**
 * 
 */
package com.vmware.vra.cms.dao.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vra.cms.dao.model.Customer;


/**
 * @author RamS
 *
 */

public interface CustomerDaoIFace {

	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=true)
	public Customer getCustomerbyCustId(Integer custId) throws DataAccessException;
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=true)
	public Customer getCustomerbyCustName(String custName) throws DataAccessException;

	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=false)
	public	Customer saveorupdate(Customer customer) throws DataAccessException;
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=true)
	public List<Customer> listCustomers() throws DataAccessException;
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=false)
	public void deletecustomer(Customer customer)throws DataAccessException;

}
