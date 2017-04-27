package com.vmware.vra.pms.service.component.util;

import java.util.ArrayList;
import java.util.List;

import com.vmware.vra.pms.dao.model.Product;
import com.vmware.vra.pms.service.component.vo.ProductBVO;

public class ProductUtil {/*

	public static List<ProductBVO> createProductBVOList(List<Product> productList){
		
		List<ProductBVO> productBVOList=new ArrayList<ProductBVO>();
		for(Product product : productList){
			productBVOList.add(createProductBVO(product));
		}
		return productBVOList;
	}
	
	public static ProductBVO createProductBVO(Product product){

		ProductBVO productBVO = null;
		if(product!=null){
			productBVO = new ProductBVO();
			productBVO.setProductId(product.getProductId());
			productBVO.setProductName(product.getProductName());
		}
		return productBVO;
	}
	
	public static Product createProduct(ProductBVO productBVO) {
		Product product = new Product();
		product.setProductId(productBVO.getProductId());
		product.setProductName(productBVO.getProductName());
		return product;
	}
*/}
