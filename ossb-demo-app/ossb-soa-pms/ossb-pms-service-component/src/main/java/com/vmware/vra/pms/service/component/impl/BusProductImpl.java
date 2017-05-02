package com.vmware.vra.pms.service.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import com.vmware.vra.pms.dao.model.Product;
import com.vmware.vra.pms.dao.model.ProductPrice;
import com.vmware.vra.pms.dao.repositories.ProductDaoIFace;
import com.vmware.vra.pms.service.component.BusProductIFace;
import com.vmware.vra.pms.service.component.vo.CreateProductResponse;
import com.vmware.vra.pms.service.component.vo.DeleteProductResponse;
import com.vmware.vra.pms.service.component.vo.ListProductBVO;
import com.vmware.vra.pms.service.component.vo.ProductBVO;
import com.vmware.vra.pms.service.component.vo.ProductPriceBVO;
import com.vmware.vra.pms.service.component.vo.UpdateProductResponse;
/**
 * @author Nehan
 *
 */
@Component
public class BusProductImpl implements BusProductIFace{

	private static final Logger LOGGER = LoggerFactory.getLogger(BusProductImpl.class);
	@Autowired
	private ProductDaoIFace productDao;
	
	@Override
	public ListProductBVO getProduct(Integer prodId) {
		LOGGER.debug("entering into getProduct() with prodId={} ",prodId);
		ListProductBVO listProductBVO = new ListProductBVO();
		Product product = productDao.getProductbyProductId(prodId);
		List <ProductBVO> listProduct = new ArrayList <ProductBVO>();
		if(product!=null){
			ProductBVO productBVO=new ProductBVO();
			productBVO.setProductId(product.getProductId());
			productBVO.setProductName(product.getProductName());
			listProduct.add(productBVO);
			listProductBVO.setProductList(listProduct);
			listProductBVO.setStatus("success");
		}else{
			listProductBVO.setStatus("success");
		}
		LOGGER.debug("entering into getProduct()");
		return listProductBVO;
	}

	@Override
	public ListProductBVO listProducts() {
		LOGGER.debug("entering into listProducts()");
		ListProductBVO listProductBVO = new ListProductBVO();
		try{
			List<Product> productList = productDao.listProducts();
			if(productList != null && !productList.isEmpty()){
				List<ProductBVO> productBVOList = new ArrayList<ProductBVO>();//createProductBVOList(productList);
				for(Product product : productList){
					ProductBVO productBVO=new ProductBVO();
					productBVO.setProductId(product.getProductId());
					productBVO.setProductName(product.getProductName());
					if(product.getProductPriceList() != null) {
						List<ProductPriceBVO> productPriceBVOLIst = new ArrayList<ProductPriceBVO>();
						for(ProductPrice productprice : product.getProductPriceList()) {
							ProductPriceBVO productPriceBVO = new ProductPriceBVO();
							productPriceBVO.setPricePerUnit(productprice.getPricePerUnit());
							productPriceBVO.setProductPriceId(productprice.getProductPriceId());
							productPriceBVOLIst.add(productPriceBVO);
						}
						productBVO.setProductPriceBVOList(productPriceBVOLIst);
						productBVOList.add(productBVO);
					}
					listProductBVO.setProductList(productBVOList);
				} 
			}else {
				//handle the error case here
			}
		}catch(DataAccessException exe){
			listProductBVO.setStatus("failure");
			listProductBVO.setErrorDesc("Error ocurred while accessing Database");
			listProductBVO.setErrorCode("PMSPROD001");
		}
		LOGGER.debug("exiting from listProducts()");
		return listProductBVO;
	}


	@Override
	public CreateProductResponse addProduct(ProductBVO productBVO) {
		LOGGER.debug("entering into addProduct() with productBVO={} ",productBVO.toString());
		CreateProductResponse createProductResponse = new CreateProductResponse();
		try{
			if(productBVO != null){
				Product product = new Product();
				product.setProductName(productBVO.getProductName());
				product = productDao.saveorupdate(product);
				if(product.getProductId()!=null){
					productBVO.setProductId(product.getProductId());
				}
				createProductResponse.setProductBVO(productBVO);
				createProductResponse.setStatus("success");
				LOGGER.debug("product={}",product.toString());
			}
		}catch(DataAccessException exe){
			LOGGER.error("Error={}",exe);
			createProductResponse.setStatus("failure");
			createProductResponse.setErrorCode("PMSPROD002");
			createProductResponse.setErrorDesc("error ouccured while adding product");
		}
		LOGGER.debug("exiting from addProduct() with productBVO={} ",productBVO.toString());
		return createProductResponse;
	}
	
	@Override
	public UpdateProductResponse updateProduct(ProductBVO productBVO) {
		LOGGER.info("entering into updateProduct() with productBVO={} ",productBVO.toString());
		UpdateProductResponse updateProductResponse = new UpdateProductResponse();
		try{
			if(productBVO != null){
				Product product = productDao.getProductbyProductId(productBVO.getProductId());
				if(product != null){
					product.setProductId(productBVO.getProductId());
					product.setProductName(productBVO.getProductName());
					product = productDao.saveorupdate(product);
					if(product.getProductId() != null){
						productBVO.setProductId(product.getProductId());
					}
					updateProductResponse.setProductBVO(productBVO);
					updateProductResponse.setStatus("success");
					LOGGER.info("product={}",product.toString());
					
				}else{
					updateProductResponse.setStatus("failure");
					updateProductResponse.setErrorDesc("product does not exist");
					updateProductResponse.setErrorCode("PMSPROD004");
				}
			}
		}catch(DataAccessException exe){
			LOGGER.error("Error={}",exe);
			updateProductResponse.setStatus("failure");
			updateProductResponse.setErrorCode("PMSPROD009");
			updateProductResponse.setErrorDesc("error ouccured while updating product");
		}
		LOGGER.info("exiting from updateProduct() with productBVO={} ",productBVO.toString());
		return updateProductResponse;
	}

	@Override
	public DeleteProductResponse deleteProduct(Integer productId) {
		LOGGER.info("entering into deleteProduct() with productBVO={} ",productId);
		DeleteProductResponse deleteProductResponse = new DeleteProductResponse();
		try{
			if(productId!=null){
				Product product = productDao.getProductbyProductId(productId);
				if(product != null){
					//product.setCustomerStatus(false);
					product = productDao.delete(product);

					ProductBVO productBVO =new ProductBVO();
					productBVO.setProductId(product.getProductId());
					productBVO.setProductName(product.getProductName());
					deleteProductResponse.setProductBVO(productBVO);
					deleteProductResponse.setStatus("success");
					LOGGER.info("product={}",productBVO.toString());
				}else{
					deleteProductResponse.setStatus("failure");
					deleteProductResponse.setErrorDesc("product does not exist");
					deleteProductResponse.setErrorCode("PMSPROD004");
				}
			}
		}catch(DataAccessException exe){
			LOGGER.error("Error={}",exe.getMessage());
			deleteProductResponse.setStatus("failure");
			deleteProductResponse.setErrorCode("PMSPROD005");
			deleteProductResponse.setErrorDesc("error ouccured while deleting product :" + exe.getMessage());
		}
		LOGGER.info("exiting from deleteProduct() with productBVO={} ",deleteProductResponse.toString());
		return deleteProductResponse;
	}
}
