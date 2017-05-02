package com.vmware.vra.pms.dao.repositories.impl;

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

import com.vmware.vra.pms.dao.model.ProductPrice;
import com.vmware.vra.pms.dao.repositories.ProductPriceDaoIFace;

@Repository
public class ProductPriceDaoImpl extends HibernateDaoSupport implements ProductPriceDaoIFace{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductPriceDaoImpl.class);
	
	@Autowired
	public void setMySessionFactory(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	public List<ProductPrice> listProductsPrice() {
		LOGGER.debug("Entring listProductPrice()");
		List<ProductPrice> productPriceList=null;
		try {
			productPriceList=this.getHibernateTemplate().loadAll(ProductPrice.class);
		} catch(Exception e){
			e.printStackTrace();
		}
		LOGGER.debug("Exiting listProductPrice()");
		return productPriceList;
	}

	public List<ProductPrice> getProductPricebyProductId(Integer productId) {
		LOGGER.debug("Entring getProductbyProductId() with productId={}",productId);
		DetachedCriteria pagingCriteria = DetachedCriteria.forClass(ProductPrice.class);
		if(productId!=null){
			pagingCriteria.add(Restrictions.eq("product.productId", productId));
		}
		List<ProductPrice> productPricelist= (List<ProductPrice>) this.getHibernateTemplate().findByCriteria(pagingCriteria);
		LOGGER.debug("Exiting getProductPricebyProductId()");
		return productPricelist;
	
	}

	public ProductPrice getProductPriceByProductPriceId(Integer productPriceId) {

		LOGGER.debug("Entring getProductPricebyProductPriceId() with productPriceId={}",productPriceId);
		ProductPrice productPrice=null;
		DetachedCriteria pagingCriteria = DetachedCriteria.forClass(ProductPrice.class);
		if(productPriceId!=null){
			pagingCriteria.add(Restrictions.eq("productPriceId", productPriceId));
		}

		List<ProductPrice> productPricelist= (List<ProductPrice>) this.getHibernateTemplate().findByCriteria(pagingCriteria);
		if(productPricelist != null && !productPricelist.isEmpty()){
			//LOGGER.debug("entering productPricelist()={}",productPricelist.toString());
			productPrice = productPricelist.get(0);
			LOGGER.debug("exiting getproductPriceByProductPriceId()={}",productPrice.toString());
		}
		LOGGER.debug("Exiting getProductPricebyProductPriceId()");
		return productPrice;
	
	}

	public ProductPrice saveorupdate(ProductPrice productPrice) {
		//LOGGER.debug("Entring saveorupdate() with productPrice={}",productPrice.toString());
		try {
			this.getHibernateTemplate().saveOrUpdate(productPrice);
		}catch(Exception e) {
			e.printStackTrace();
		}
		LOGGER.debug("Exiting saveorupdate()");
		return productPrice;
	}

	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=false)
	public ProductPrice delete(ProductPrice productPrice) throws DataAccessException{
		
		try {
			this.getHibernateTemplate().delete(productPrice);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return productPrice;
	}
}
