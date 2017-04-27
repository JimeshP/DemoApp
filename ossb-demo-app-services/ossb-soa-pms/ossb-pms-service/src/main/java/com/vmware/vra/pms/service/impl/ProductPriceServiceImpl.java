package com.vmware.vra.pms.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vmware.vra.pms.service.ProductPriceServiceIFace;
import com.vmware.vra.pms.service.component.BusProductPriceIFace;
import com.vmware.vra.pms.service.component.vo.CreateProductPriceResponse;
import com.vmware.vra.pms.service.component.vo.DeleteProductPriceResponse;
import com.vmware.vra.pms.service.component.vo.ListProductPriceBVO;
import com.vmware.vra.pms.service.component.vo.ProductPriceBVO;
import com.vmware.vra.pms.service.component.vo.UpdateProductPriceResponse;

@Controller
public class ProductPriceServiceImpl implements ProductPriceServiceIFace{

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductPriceServiceImpl.class);
	
	@Autowired
	private BusProductPriceIFace busProductPriceIFace;

	/**
	 * listProductPrice()
	 */
	@Override
	@RequestMapping(value="/productpriceservice", method = RequestMethod.GET)
	public ResponseEntity<ListProductPriceBVO> listProductPrice() {
		HttpHeaders responseHeaders = new HttpHeaders();
		ListProductPriceBVO	listProductPriceBVO = busProductPriceIFace.listProductPrice();
		return new ResponseEntity(listProductPriceBVO, responseHeaders, HttpStatus.OK);
	}

	/**
	 * listProductPriceByProductId()
	 */
	@Override
	@RequestMapping(value="/productpriceservice/product/{productid}", method = RequestMethod.GET)
	public ResponseEntity<ListProductPriceBVO> listProductPriceByProductId(@PathVariable("productid") Integer productid) {
		HttpHeaders responseHeaders = new HttpHeaders();
		ListProductPriceBVO listProductPriceBVO = busProductPriceIFace.getProductPriceByProductId(productid);
		return new ResponseEntity(listProductPriceBVO, responseHeaders, HttpStatus.OK);
	}

	/**
	 * listProductPriceByProductPriceId()
	 */
	@Override
	@RequestMapping(value="/productpriceservice/{productpriceid}", method = RequestMethod.GET)
	public ResponseEntity<ListProductPriceBVO> listProductPriceByProductPriceId(@PathVariable("productpriceid") Integer productpriceid) {
		HttpHeaders responseHeader = new HttpHeaders();
		ProductPriceBVO productpriceBVO = busProductPriceIFace.getProductPriceByProductPriceId(productpriceid);
		return new ResponseEntity(productpriceBVO, responseHeader, HttpStatus.OK);
	}


	/**
	 * addProductPrice()
	 */
	@Override
	@RequestMapping(value="/productpriceservice", method = RequestMethod.PUT)
	public ResponseEntity<CreateProductPriceResponse> addProductPrice(HttpServletRequest req, @RequestBody ProductPriceBVO productPriceBVO) throws Exception {
		HttpHeaders responseHeaders = new HttpHeaders();
		CreateProductPriceResponse createProductPriceResponse = busProductPriceIFace.addProductPrice(productPriceBVO);
		return new ResponseEntity(createProductPriceResponse, responseHeaders, HttpStatus.OK);
	}

	/**
	 * deleteProductPrice()
	 */
	@Override
	@RequestMapping(value="/productpriceservice/{productpriceid}",method = RequestMethod.DELETE)
	public ResponseEntity<DeleteProductPriceResponse> deleteProductPrice(@PathVariable("productpriceid") Integer productpriceid) {
		HttpHeaders responseHeaders = new HttpHeaders();
		DeleteProductPriceResponse deleteProductPriceResponse = busProductPriceIFace.deleteProductPrice(productpriceid);
		return new ResponseEntity(deleteProductPriceResponse, responseHeaders, HttpStatus.OK);
	}

	/**
	 * updateProductPrice()
	 */
	@Override
	@RequestMapping(value="/productpriceservice", method = RequestMethod.POST)
	public ResponseEntity<UpdateProductPriceResponse> updateProductPrice(HttpServletRequest req, @RequestBody ProductPriceBVO productPriceBVO) throws Exception {
		LOGGER.info("Entring updateProductPrice() service :: " + productPriceBVO.toString());
		HttpHeaders responseHeaders = new HttpHeaders();
		UpdateProductPriceResponse updateProductPriceResponse = busProductPriceIFace.updateProductPrice(productPriceBVO);
		LOGGER.info("exiting updateProductPrice() service :" + updateProductPriceResponse.toString());
		return new ResponseEntity(updateProductPriceResponse, responseHeaders, HttpStatus.OK);
	}

}
