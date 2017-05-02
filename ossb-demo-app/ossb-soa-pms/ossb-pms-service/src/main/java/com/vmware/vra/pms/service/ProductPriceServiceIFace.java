package com.vmware.vra.pms.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vmware.vra.pms.service.component.vo.CreateProductPriceResponse;
import com.vmware.vra.pms.service.component.vo.DeleteProductPriceResponse;
import com.vmware.vra.pms.service.component.vo.DeleteProductResponse;
import com.vmware.vra.pms.service.component.vo.ListProductPriceBVO;
import com.vmware.vra.pms.service.component.vo.ProductPriceBVO;
import com.vmware.vra.pms.service.component.vo.UpdateProductPriceResponse;


/**
 * @author Nehan
 *
 */

@Controller
public interface ProductPriceServiceIFace {

	@RequestMapping(value="/productpriceservice" , method = RequestMethod.GET)
	public ResponseEntity<ListProductPriceBVO> listProductPrice();
	
	@RequestMapping(value="/productpriceservice/{productid}"  ,method = RequestMethod.GET)
	public ResponseEntity<ListProductPriceBVO> listProductPriceByProductId(@PathVariable("productid") Integer productid);
	
	@RequestMapping(value="/productpriceservice/{productpriceid}" ,method = RequestMethod.GET)
	public ResponseEntity<ListProductPriceBVO> listProductPriceByProductPriceId(@PathVariable("productpriceid") Integer productpriceid);
	
	@RequestMapping(value="/productpriceservice", method = RequestMethod.PUT)
	public ResponseEntity<CreateProductPriceResponse> addProductPrice(HttpServletRequest req, @RequestBody ProductPriceBVO productPriceBVO) throws Exception;
	
	@RequestMapping(value="/productpriceservice", method = RequestMethod.POST)
	public ResponseEntity<UpdateProductPriceResponse> updateProductPrice(HttpServletRequest req, @RequestBody ProductPriceBVO productPriceBVO) throws Exception;
	
	@RequestMapping(value="/productpriceservice/{productpriceid}",method = RequestMethod.DELETE)
	public ResponseEntity<DeleteProductPriceResponse> deleteProductPrice(@PathVariable("productpriceid") Integer productpriceid);
	
//	@RequestMapping(value="/productpriceservice", method = RequestMethod.POST)
//	public ResponseEntity<CreateProductPriceResponse> updateUser(HttpServletRequest req,
//			@RequestBody ProductPriceBVO productPriceBVO) throws Exception;
	
}
