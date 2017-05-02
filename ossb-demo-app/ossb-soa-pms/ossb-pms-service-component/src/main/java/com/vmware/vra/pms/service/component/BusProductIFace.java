package com.vmware.vra.pms.service.component;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vra.pms.service.component.vo.CreateProductResponse;
import com.vmware.vra.pms.service.component.vo.DeleteProductResponse;
import com.vmware.vra.pms.service.component.vo.ListProductBVO;
import com.vmware.vra.pms.service.component.vo.ProductBVO;
import com.vmware.vra.pms.service.component.vo.UpdateProductResponse;


public interface BusProductIFace {
	
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public ListProductBVO getProduct(Integer prodId);
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public ListProductBVO listProducts();
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public CreateProductResponse addProduct(ProductBVO productBVO);

	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public UpdateProductResponse updateProduct(ProductBVO productBVO);

	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public DeleteProductResponse deleteProduct(Integer productId);
	
}
