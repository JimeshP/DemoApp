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

import com.vmware.vra.cms.dao.model.Address;
import com.vmware.vra.cms.dao.repositories.AddressDaoIFace;

/**
 * @author RamS
 *
 */
@Repository
public class AddressDaoImpl extends HibernateDaoSupport implements
AddressDaoIFace {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddressDaoImpl.class);

	@Autowired
	public void setMySessionFactory(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly = false)
	public Address saveorupdate(Address address) throws DataAccessException {
		LOGGER.debug(">>Entering in saveorupdate()");
		this.getHibernateTemplate().saveOrUpdate(address);
		LOGGER.debug(">>Exiting from saveorupdate()");
		return address;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly = true)
	public List<Address> listAddress() throws DataAccessException {
		LOGGER.debug(">>Entering in listAddress()");
		List<Address>  addresslist=this.getHibernateTemplate().loadAll(Address.class);
		LOGGER.debug(">>Exiting from listAddress()");
		return addresslist;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly = true)
	public List<Address> listAddressByCustId(Integer custId) throws DataAccessException {
		LOGGER.debug(">>Entering in listAddressByCustId() with custId={}",custId);
		List<Address> addresslist=null;
		DetachedCriteria pagingCriteria = DetachedCriteria.forClass(Address.class);
		if(custId!=null){
			pagingCriteria.add(Restrictions.eq("customer.customerId", custId));
		}
		addresslist= (List<Address>) this.getHibernateTemplate().findByCriteria(pagingCriteria);
		LOGGER.debug(">>Exiting from listAddressByCustId() with addresslist={}",addresslist);
		return addresslist;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly = true)
	public List<Address> listAddressByAddressId(Integer addressId) throws DataAccessException {
		LOGGER.debug(">>Entering in listAddressByAddressId() with addressId={}",addressId);
		List<Address> addresslist=null;
		DetachedCriteria pagingCriteria = DetachedCriteria.forClass(Address.class);
		if(addressId!=null){
			pagingCriteria.add(Restrictions.eq("addressId", addressId));
		}
		addresslist= (List<Address>) this.getHibernateTemplate().findByCriteria(pagingCriteria);
		LOGGER.debug(">>Exiting from listAddressByCustId() with addresslist={}",addresslist);
		return addresslist;
	}


	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteAddress(Address address) throws DataAccessException {
		LOGGER.debug(">>Entering in deleteAddress()");
		this.getHibernateTemplate().delete(address);
		LOGGER.debug(">>Entering in deleteAddress()");
	}
}

