package com.vmware.vra.pms.dao.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vra.pms.dao.model.Product;
import com.vmware.vra.pms.dao.model.ProductPrice;

@Repository
public interface ProductPriceDaoIFace {
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=true)
	public List<ProductPrice> listProductsPrice();
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=true)
	public List<ProductPrice> getProductPricebyProductId(Integer productId);

	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=true)
	public ProductPrice getProductPriceByProductPriceId(Integer productPriceId);
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=false)
	public ProductPrice saveorupdate(ProductPrice productPrice);
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=false)
	public ProductPrice delete(ProductPrice productPrice);
}
