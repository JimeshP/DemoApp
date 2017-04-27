package com.vmware.vra.pms.service.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vra.pms.dao.model.Product;
import com.vmware.vra.pms.dao.model.ProductPrice;
import com.vmware.vra.pms.dao.repositories.ProductDaoIFace;
import com.vmware.vra.pms.dao.repositories.ProductPriceDaoIFace;
import com.vmware.vra.pms.service.component.BusProductPriceIFace;
import com.vmware.vra.pms.service.component.vo.CreateProductPriceResponse;
import com.vmware.vra.pms.service.component.vo.DeleteProductPriceResponse;
import com.vmware.vra.pms.service.component.vo.ListProductPriceBVO;
import com.vmware.vra.pms.service.component.vo.ProductPriceBVO;
import com.vmware.vra.pms.service.component.vo.UpdateProductPriceResponse;

@Component
public class BusProductPriceImpl implements BusProductPriceIFace{

	@Autowired
	private ProductPriceDaoIFace productPriceDaoIFace;
	
	@Autowired
	private ProductDaoIFace productDaoIFace;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BusProductPriceImpl.class);
	
	@Override
	public ListProductPriceBVO getProductPriceByProductId(Integer productId) {
		LOGGER.debug("Entring getProductPriceByProductId()");
		ListProductPriceBVO listProductPriceBVO = new ListProductPriceBVO();
		try{
			List<ProductPrice> productPriceList = productPriceDaoIFace.getProductPricebyProductId(productId);
			if(productPriceList!=null && !productPriceList.isEmpty()){
				LOGGER.debug("Product Price size= {}",productPriceList.size());
				List<ProductPriceBVO> productPriceBVOList = new ArrayList<ProductPriceBVO>();
				for(ProductPrice productPrice : productPriceList){
					ProductPriceBVO productPriceBVO = new ProductPriceBVO();
					productPriceBVO.setProductPriceId(productPrice.getProductPriceId());
					productPriceBVO.setPricePerUnit(productPrice.getPricePerUnit());
					productPriceBVO.setProductPRODID(productPrice.getProduct().getProductId());
					productPriceBVOList.add(productPriceBVO);
				}
				listProductPriceBVO.setProductPriceList(productPriceBVOList);
				listProductPriceBVO.setStatus("success");
			} else {
				listProductPriceBVO.setStatus("success");
			}
		}catch(DataAccessException exe){
			LOGGER.error("execetion occured db layer");
		}
		LOGGER.debug("Exiting getProductPriceByProductId() with productPriceBVO={}",listProductPriceBVO.toString());
		return listProductPriceBVO;	
	}

	@Override
	public ProductPriceBVO getProductPriceByProductPriceId(Integer productPriceId) {

		LOGGER.debug("Entring getProductPriceByProductId()");
		ProductPriceBVO productPriceBVO = new ProductPriceBVO();
		try{
			ProductPrice productPrice = productPriceDaoIFace.getProductPriceByProductPriceId(productPriceId);
			if(productPrice!=null){
				LOGGER.debug("Product Price= {}",productPrice.toString());
				productPriceBVO = new ProductPriceBVO();
				productPriceBVO.setProductPriceId(productPrice.getProductPriceId());
				productPriceBVO.setPricePerUnit(productPrice.getPricePerUnit());
				productPriceBVO.setProductPRODID(productPrice.getProduct().getProductId());
			} 
		}catch(DataAccessException exe){
			LOGGER.error("execetion occured db layer");
		}
		LOGGER.debug("Exiting getProductPriceByProductId() with productPriceBVO={}",productPriceBVO.toString());
		return productPriceBVO;
	
	}

	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public ListProductPriceBVO listProductPrice() {

		LOGGER.debug("Entring listProductPrice()");
		ListProductPriceBVO listProductPriceBVO = new ListProductPriceBVO();
		try{
			List<ProductPrice> productPriceList = productPriceDaoIFace.listProductsPrice();

			if(productPriceList!=null && !productPriceList.isEmpty()){
				LOGGER.debug("listProductPrice() size= {}",productPriceList.size());
				List<ProductPriceBVO> productPriceBVOList = new ArrayList<ProductPriceBVO>();
				for(ProductPrice productPrice : productPriceList){
					ProductPriceBVO productPriceBVO = new ProductPriceBVO();
					productPriceBVO.setProductPriceId(productPrice.getProductPriceId());
					productPriceBVO.setPricePerUnit(productPrice.getPricePerUnit());
					productPriceBVO.setProductPRODID(productPrice.getProduct().getProductId());
					productPriceBVOList.add(productPriceBVO);
				}
				listProductPriceBVO.setProductPriceList(productPriceBVOList);
				listProductPriceBVO.setStatus("success");
			} else {
				listProductPriceBVO.setStatus("success");
			}
		}catch(DataAccessException exe){
			listProductPriceBVO.setStatus("failure");
			listProductPriceBVO.setErrorDesc("Error ocurred while accessing Database");
			listProductPriceBVO.setErrorCode("DB001");
		}
		LOGGER.debug("Exiting listUsers()");
		return listProductPriceBVO;
	}

	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public CreateProductPriceResponse addProductPrice(ProductPriceBVO productPriceBVO) throws Exception {

		LOGGER.debug("Entring addProductPrice()");
		CreateProductPriceResponse createProductPriceResponse = new CreateProductPriceResponse();
		try{
			if(productPriceBVO != null ){
					ProductPrice productPrice = null;
					if(productPriceBVO.getProductPRODID()!=null){
						Product product = productDaoIFace.getProductbyProductId(productPriceBVO.getProductPRODID());
						productPrice = new ProductPrice();
						if(productPriceBVO.getPricePerUnit()!=null){
							productPrice.setPricePerUnit(productPriceBVO.getPricePerUnit());
						}

						productPrice.setProduct(product);
						productPrice = productPriceDaoIFace.saveorupdate(productPrice);
					}

					if(productPrice != null){
						productPriceBVO = new ProductPriceBVO();
						productPriceBVO.setProductPriceId(productPrice.getProductPriceId());
						productPriceBVO.setPricePerUnit(productPrice.getPricePerUnit());
						productPriceBVO.setProductPRODID(productPrice.getProduct().getProductId());
						createProductPriceResponse.setProductPriceBVO(productPriceBVO);
						createProductPriceResponse.setStatus("success");
					}
			}
		}catch(Exception exe){
			LOGGER.error("Execetion={}",exe);
			throw exe;
		}
		LOGGER.debug("Exiting addProductPrice() with productPriceBVO={}",productPriceBVO.toString());
		return createProductPriceResponse;
	}

	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public DeleteProductPriceResponse deleteProductPrice(Integer productPriceId) {

		LOGGER.debug("entering into deleteProductPrice() with productPriceBVO={} ",productPriceId);
		DeleteProductPriceResponse deleteProductPriceResponse = new DeleteProductPriceResponse();
		try{
			if(productPriceId!=null){
				ProductPrice productPrice = productPriceDaoIFace.getProductPriceByProductPriceId(productPriceId);
				if(productPrice != null){
					//product.setCustomerStatus(false);
					productPrice = productPriceDaoIFace.delete(productPrice);

					ProductPriceBVO productPriceBVO =new ProductPriceBVO();
					productPriceBVO.setProductPriceId(productPrice.getProductPriceId());
					productPriceBVO.setPricePerUnit(productPrice.getPricePerUnit());
					productPriceBVO.setProductPRODID(productPrice.getProduct().getProductId());
					deleteProductPriceResponse.setProductPriceBVO(productPriceBVO);
					deleteProductPriceResponse.setStatus("success");
					LOGGER.info("productprice={}",productPriceBVO.toString());
				}else{
					deleteProductPriceResponse.setStatus("failure");
					deleteProductPriceResponse.setErrorDesc("product price does not exist");
					deleteProductPriceResponse.setErrorCode("PMSPROD006");
				}
			}
		}catch(DataAccessException exe){
			LOGGER.error("Error={}",exe);
			deleteProductPriceResponse.setStatus("failure");
			deleteProductPriceResponse.setErrorCode("PMSPROD007");
			deleteProductPriceResponse.setErrorDesc("error ouccured while deleting product price");
		}
		LOGGER.debug("exiting from deleteProductPrice() with productPriceBVO={} ",deleteProductPriceResponse.toString());
		return deleteProductPriceResponse;
	
	}

	@Override
	public UpdateProductPriceResponse updateProductPrice(ProductPriceBVO productPriceBVO) throws Exception {
		LOGGER.info("Entring updateProductPrice()");
		UpdateProductPriceResponse updateProductPriceResponse = new UpdateProductPriceResponse();
		try{
			if(productPriceBVO != null ){
					ProductPrice productPrice = null;
					if(productPriceBVO.getProductPRODID()!=null){
						Product product = productDaoIFace.getProductbyProductId(productPriceBVO.getProductPRODID());
						productPrice = new ProductPrice();
						productPrice.setProductPriceId(productPriceBVO.getProductPriceId());
						if(productPriceBVO.getPricePerUnit()!=null){
							productPrice.setPricePerUnit(productPriceBVO.getPricePerUnit());
						}

						productPrice.setProduct(product);
						LOGGER.info("productPrice business component1 ::" + productPrice.toString());
						productPrice = productPriceDaoIFace.saveorupdate(productPrice);
					}
					LOGGER.info("productPrice business component 2::" + productPrice.toString());
					if(productPrice != null){
						productPriceBVO = new ProductPriceBVO();
						productPriceBVO.setProductPriceId(productPrice.getProductPriceId());
						productPriceBVO.setPricePerUnit(productPrice.getPricePerUnit());
						productPriceBVO.setProductPRODID(productPrice.getProduct().getProductId());
						updateProductPriceResponse.setProductPriceBVO(productPriceBVO);
						updateProductPriceResponse.setStatus("success");
					}
			}
		}catch(Exception exe){
			LOGGER.error("Execetion={}",exe);
			throw exe;
		}
		LOGGER.info("Exiting updateProductPrice() with productPriceBVO={}",productPriceBVO.toString());
		return updateProductPriceResponse;
	
	}
}
