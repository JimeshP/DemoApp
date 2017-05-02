package com.vmware.vra.pms.dao.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vra.pms.dao.model.Product;

@Repository
public interface ProductDaoIFace {
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=true)
	public List<Product> listProducts();
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=true)
	public Product getProductbyProductId(Integer productId);

	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=true)
	public Product getProductByProductName(String productName);
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=false)
	public Product saveorupdate(Product product);
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=false)
	public Product delete(Product product);
}
