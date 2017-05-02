package com.vmware.vra.pms.dao.repositories.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vra.pms.dao.model.Product;
import com.vmware.vra.pms.dao.repositories.ProductDaoIFace;

@Repository
public class ProductDaoImpl extends HibernateDaoSupport implements ProductDaoIFace{
	
	@Autowired
	public void setMySessionFactory(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=true)
	public List<Product> listProducts() throws DataAccessException {
		List<Product> productList=null;
		productList=this.getHibernateTemplate().loadAll(Product.class);
		return productList;
	}

	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=true)
	public Product getProductbyProductId(Integer productId) throws DataAccessException {
		Product product = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		if(productId!=null){
			criteria.add(Restrictions.eq("productId", productId));
		}
		
		List<Product> productlist= (List<Product>) this.getHibernateTemplate().findByCriteria(criteria);
		if(productlist != null && !productlist.isEmpty()){
			product = productlist.get(0);
		}
		return product;
	}

	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=true)
	public Product getProductByProductName(String productName) throws DataAccessException {
		Product product=null;
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		if(productName!=null){
			criteria.add(Restrictions.eq("productName", productName));
		}
		
		List<Product> productlist= (List<Product>) this.getHibernateTemplate().findByCriteria(criteria);
		if(productlist!=null && !productlist.isEmpty()){
			product = productlist.get(0);
		}
		return product;
	}
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=false)
	public Product saveorupdate(Product product) throws DataAccessException{
		
		try {
			this.getHibernateTemplate().saveOrUpdate(product);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return product;
	}
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=false)
	public Product delete(Product product) throws DataAccessException{
		
		try {
			this.getHibernateTemplate().delete(product);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return product;
	}

}
