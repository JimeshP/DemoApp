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

import com.vmware.vra.pms.service.ProductServiceIFace;
import com.vmware.vra.pms.service.component.BusProductIFace;
import com.vmware.vra.pms.service.component.vo.CreateProductResponse;
import com.vmware.vra.pms.service.component.vo.DeleteProductResponse;
import com.vmware.vra.pms.service.component.vo.ListProductBVO;
import com.vmware.vra.pms.service.component.vo.ProductBVO;
import com.vmware.vra.pms.service.component.vo.UpdateProductResponse;

@Controller
public class ProductServiceImpl implements ProductServiceIFace{
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Autowired
	private BusProductIFace busProductIFace;

	/**
	 * listProducts()
	 */
	@Override
	@RequestMapping(value="/productservice",method = RequestMethod.GET)
	public ResponseEntity<ListProductBVO> listProducts() {
		HttpHeaders responseHeaders = new HttpHeaders();
		ListProductBVO	listProductBVO = busProductIFace.listProducts();
		return new ResponseEntity(listProductBVO, responseHeaders, HttpStatus.OK);
	}

	/**
	 * listProduct(Integer productid)
	 */
	@Override
	@RequestMapping(value="/productservice/{productid}", method = RequestMethod.GET)
	public ResponseEntity<ListProductBVO> listProduct(@PathVariable("productid") Integer productid) {
		HttpHeaders responseHeaders = new HttpHeaders();
		ListProductBVO	listProductBVO = busProductIFace.getProduct(productid);
		return new ResponseEntity(listProductBVO, responseHeaders, HttpStatus.OK);
	}

	/**
	 * addProduct()
	 */
	@Override
	@RequestMapping(value="/productservice",method = RequestMethod.PUT)
	public ResponseEntity<CreateProductResponse> addProduct(HttpServletRequest req, @RequestBody ProductBVO productBVO) {
		HttpHeaders responseHeaders = new HttpHeaders();
		CreateProductResponse createProductResponse = busProductIFace.addProduct(productBVO);
		return new ResponseEntity(createProductResponse, responseHeaders, HttpStatus.OK);
	}

	/**
	 * deleteProduct()
	 */
	@Override
	@RequestMapping(value="/productservice/{productid}",method = RequestMethod.DELETE)
	public ResponseEntity<DeleteProductResponse> deleteProduct(@PathVariable("productid") Integer productid) {
		LOGGER.info("entering into deleteProduct() service ={} ",productid);
		HttpHeaders responseHeaders = new HttpHeaders();
		DeleteProductResponse deleteProductResponse = busProductIFace.deleteProduct(productid);
		LOGGER.info("exiting into deleteProduct() service ={} ",deleteProductResponse.toString());
		return new ResponseEntity(deleteProductResponse, responseHeaders, HttpStatus.OK);
	}

	/**
	 * updateProduct()
	 */
	@Override
	@RequestMapping(value="/productservice",method = RequestMethod.POST)
	public ResponseEntity<UpdateProductResponse> updateProduct(HttpServletRequest req, @RequestBody ProductBVO productBVO) {
		LOGGER.info("entering into updateProduct() service ={} ",productBVO.toString());
		HttpHeaders responseHeaders = new HttpHeaders();
		UpdateProductResponse updateProductResponse = busProductIFace.updateProduct(productBVO);
		LOGGER.info("exiting into updateProduct() service ={} ",updateProductResponse.toString());
		return new ResponseEntity(updateProductResponse, responseHeaders, HttpStatus.OK);
	}

}
