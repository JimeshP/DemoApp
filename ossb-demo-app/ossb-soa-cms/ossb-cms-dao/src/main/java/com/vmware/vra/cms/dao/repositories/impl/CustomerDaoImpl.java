/**
 * 
 */
package com.vmware.vra.cms.dao.repositories.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vra.cms.dao.model.Customer;
import com.vmware.vra.cms.dao.repositories.CustomerDaoIFace;

/**
 * @author RamS
 *
 */

@Repository
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDaoIFace {
	
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDaoImpl.class);

	@Autowired
	public void setMySessionFactory(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=true)
	public Customer getCustomerbyCustId(Integer custId) throws DataAccessException{
		Customer customer=null;
		DetachedCriteria pagingCriteria = DetachedCriteria.forClass(Customer.class);
		if(custId!=null){
			pagingCriteria.add(Restrictions.eq("customerId", custId));
		}
		List<Customer> customerlist= (List<Customer>) this.getHibernateTemplate().findByCriteria(pagingCriteria);
		if(customerlist!=null && !customerlist.isEmpty()){
			customer=customerlist.get(0);
		}
		return customer;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=true)
	public Customer getCustomerbyCustName(String custName) throws DataAccessException{
		Customer customer=null;
		DetachedCriteria pagingCriteria = DetachedCriteria.forClass(Customer.class);
		if(custName!=null){
			pagingCriteria.add(Restrictions.eq("customerName", custName));
		}
		List<Customer> customerlist= (List<Customer>) this.getHibernateTemplate().findByCriteria(pagingCriteria);
		if(customerlist!=null && !customerlist.isEmpty()){
			customer=customerlist.get(0);
		}
		return customer;
	}
	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=false)
	public Customer saveorupdate(Customer customer)throws DataAccessException {
		LOGGER.debug("Entring saveorupdate()");
		this.getHibernateTemplate().saveOrUpdate(customer);
		LOGGER.debug("Exiting saveorupdate()");
		return customer;
	}
	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=false)
	public void deletecustomer(Customer customer)throws DataAccessException {
		LOGGER.debug("Entring deletecustomer()");
		this.getHibernateTemplate().delete(customer);
		LOGGER.debug("Exiting deletecustomer()");
	}
	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=true)
	public List<Customer> listCustomers() throws DataAccessException {
		LOGGER.debug("Entring listCustomers()");
		List<Customer> customerList=null;
		customerList=this.getHibernateTemplate().loadAll(Customer.class);
		LOGGER.debug("Exiting listCustomers()");
		return customerList;
	}
	
}
