package com.vmware.vra.pms.service.component;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vra.pms.service.component.vo.CreateProductPriceResponse;
import com.vmware.vra.pms.service.component.vo.DeleteProductPriceResponse;
import com.vmware.vra.pms.service.component.vo.ListProductPriceBVO;
import com.vmware.vra.pms.service.component.vo.ProductPriceBVO;
import com.vmware.vra.pms.service.component.vo.UpdateProductPriceResponse;

/**
 * @author Nehan
 *
 */

public interface BusProductPriceIFace {
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public ListProductPriceBVO getProductPriceByProductId(Integer productId);
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public ProductPriceBVO getProductPriceByProductPriceId(Integer productPriceId);
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public ListProductPriceBVO listProductPrice();
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public CreateProductPriceResponse addProductPrice(ProductPriceBVO productPriceBVO) throws Exception;
	

	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public DeleteProductPriceResponse deleteProductPrice(Integer productPriceId);

	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public UpdateProductPriceResponse updateProductPrice(ProductPriceBVO productPriceBVO) throws Exception;;
	
//	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
//	public CreateUserResponse updateUser(UserBVO userBVO) throws Exception;
}
