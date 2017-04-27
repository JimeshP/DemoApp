package com.vmware.vra.pms.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vmware.vra.pms.service.component.vo.CreateProductResponse;
import com.vmware.vra.pms.service.component.vo.DeleteProductResponse;
import com.vmware.vra.pms.service.component.vo.ListProductBVO;
import com.vmware.vra.pms.service.component.vo.ProductBVO;
import com.vmware.vra.pms.service.component.vo.UpdateProductResponse;

@Controller() 
public interface ProductServiceIFace {
	
	@RequestMapping(value="/productservice",method = RequestMethod.GET)
	public ResponseEntity<ListProductBVO>  listProducts();
	
	@RequestMapping(value="/productservice/{productid}",method = RequestMethod.GET)
	public ResponseEntity<ListProductBVO>  listProduct(@PathVariable("productid") Integer productid);
	
	@RequestMapping(value="/productservice",method = RequestMethod.PUT)
	public ResponseEntity< CreateProductResponse> addProduct(HttpServletRequest req, @RequestBody ProductBVO productBVO );
	
	@RequestMapping(value="/productservice",method = RequestMethod.POST)
	public ResponseEntity< UpdateProductResponse> updateProduct(HttpServletRequest req, @RequestBody ProductBVO productBVO );

	@RequestMapping(value="/productservice/{productid}",method = RequestMethod.DELETE)
	public ResponseEntity<DeleteProductResponse> deleteProduct(@PathVariable("productid") Integer productid);
	
}
