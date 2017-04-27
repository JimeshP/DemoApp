/**
 * 
 */
package com.vmware.vra.cms.service.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vra.cms.dao.model.Address;
import com.vmware.vra.cms.dao.model.Customer;
import com.vmware.vra.cms.dao.repositories.AddressDaoIFace;
import com.vmware.vra.cms.dao.repositories.CustomerDaoIFace;
import com.vmware.vra.cms.service.component.BusAddressIFace;
import com.vmware.vra.cms.service.component.vo.AddressBVO;
import com.vmware.vra.cms.service.component.vo.CreateAddressResponse;
import com.vmware.vra.cms.service.component.vo.DeleteAddressResponse;
import com.vmware.vra.cms.service.component.vo.ListAddressResponse;

/**
 * @author RamS
 *
 */
@Component
public class BusAddressImpl implements BusAddressIFace {

	private static final Logger LOGGER = LoggerFactory.getLogger(BusAddressImpl.class);

	@Autowired
	private AddressDaoIFace addressDao;

	@Autowired
	private CustomerDaoIFace customerDao;

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public ListAddressResponse listAddress()throws Exception {
		LOGGER.debug(">>Entering into listAddress()");
		ListAddressResponse listAddressResponse=new ListAddressResponse();
		List<Address> addresslist=addressDao.listAddress();
		if(addresslist!=null && !addresslist.isEmpty()){
			List<AddressBVO> addressBVOlist=new ArrayList<AddressBVO>();
			for(Address address:addresslist){
				AddressBVO addressBVO=new AddressBVO();
				addressBVO.setAddressId(address.getAddressId());
				addressBVO.setAddressFirstLine(address.getAddressFirstLine());
				addressBVO.setAddressSecondLine(address.getAddressSecondLine());
				addressBVO.setCity(address.getCity());
				addressBVO.setCountry(address.getCountry());
				addressBVO.setCustomerId(address.getCustomer().getCustomerId());
				addressBVO.setState(address.getState());
				addressBVO.setZipCode(address.getZipCode());
				addressBVOlist.add(addressBVO);
			}
			listAddressResponse.setAddresslist(addressBVOlist);
			listAddressResponse.setStatus("success");
		}else{
			listAddressResponse.setStatus("success");
		}
		LOGGER.debug(">>Exiting from listAddress()");
		return listAddressResponse;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public CreateAddressResponse addAddress(AddressBVO addressBVO)throws Exception {
		LOGGER.debug(">>Entering into addAddress()");
		CreateAddressResponse createAddressResponse =new CreateAddressResponse();
		if(addressBVO!=null){
			LOGGER.debug(">>Entering into addAddress() with addressBVO={}",addressBVO.toString());
			Address address=new Address();
			if(addressBVO.getAddressId()!=null){
				address.setAddressId(addressBVO.getAddressId());
			}
			if(addressBVO.getAddressFirstLine()!=null){
				address.setAddressFirstLine(addressBVO.getAddressFirstLine().trim());
			}
			if(addressBVO.getAddressSecondLine()!=null){
				address.setAddressSecondLine(addressBVO.getAddressSecondLine().trim());
			}

			if(addressBVO.getCity()!=null){
				address.setCity(addressBVO.getCity().trim());
			}

			if(addressBVO.getState()!=null){
				address.setState(addressBVO.getState().trim());
			}
			if(addressBVO.getCountry()!=null){
				address.setCountry(addressBVO.getCountry().trim());
			}

			if(addressBVO.getZipCode()!=null){
				address.setZipCode(addressBVO.getZipCode());
			}

			Customer customer=customerDao.getCustomerbyCustId(addressBVO.getCustomerId());
			if(customer!=null){
				address.setCustomer(customer);
			}

			address=addressDao.saveorupdate(address);
			if(address!=null){
				addressBVO.setAddressId(address.getAddressId());
			}
			createAddressResponse.setAddressBVO(addressBVO);
			createAddressResponse.setStatus("success");

		}
		LOGGER.debug(">>Exiting from addAddress()");
		return createAddressResponse;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW, readOnly = true)
	public ListAddressResponse listAddressByCustomerId(Integer custId) throws Exception {
		LOGGER.debug(">>Entering into listAddressByCustomerId()");
		ListAddressResponse listAddressResponse=new ListAddressResponse();
		if(custId!=null){
			List<Address> addresslist=addressDao.listAddressByCustId(custId);
			if(addresslist!=null && !addresslist.isEmpty()){
				List<AddressBVO> addressBVOlist=new ArrayList<AddressBVO>();
				for(Address address:addresslist){
					AddressBVO addressBVO=new AddressBVO();
					addressBVO.setAddressId(address.getAddressId());
					addressBVO.setAddressFirstLine(address.getAddressFirstLine());
					addressBVO.setAddressSecondLine(address.getAddressSecondLine());
					addressBVO.setCity(address.getCity());
					addressBVO.setCountry(address.getCountry());
					addressBVO.setCustomerId(address.getCustomer().getCustomerId());
					addressBVO.setState(address.getState());
					addressBVO.setZipCode(address.getZipCode());
					addressBVOlist.add(addressBVO);
				}
				listAddressResponse.setAddresslist(addressBVOlist);
				listAddressResponse.setStatus("success");
			}else{
				listAddressResponse.setStatus("success");
			}
		}
		LOGGER.debug(">>Exiting from listAddressByCustomerId()");
		return listAddressResponse;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW, readOnly = true)
	public ListAddressResponse listAddressByAddressId(Integer addressId)
			throws Exception {
		LOGGER.debug(">>Entering into listAddressByCustomerId()");
		ListAddressResponse listAddressResponse=new ListAddressResponse();
		if(addressId!=null){
			List<Address> addresslist=addressDao.listAddressByAddressId(addressId);
			if(addresslist!=null && !addresslist.isEmpty()){
				List<AddressBVO> addressBVOlist=new ArrayList<AddressBVO>();
				for(Address address:addresslist){
					AddressBVO addressBVO=new AddressBVO();
					addressBVO.setAddressId(address.getAddressId());
					addressBVO.setAddressFirstLine(address.getAddressFirstLine());
					addressBVO.setAddressSecondLine(address.getAddressSecondLine());
					addressBVO.setCity(address.getCity());
					addressBVO.setCountry(address.getCountry());
					addressBVO.setCustomerId(address.getCustomer().getCustomerId());
					addressBVO.setState(address.getState());
					addressBVO.setZipCode(address.getZipCode());
					addressBVOlist.add(addressBVO);
				}
				listAddressResponse.setAddresslist(addressBVOlist);
				listAddressResponse.setStatus("success");
			}else{
				listAddressResponse.setStatus("success");
			}
		}
		LOGGER.debug(">>Exiting from listAddressByCustomerId()");
		return listAddressResponse;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public DeleteAddressResponse deleteAddress(Integer addressId) {
		LOGGER.debug(">>Entering into deleteAddress()");
		DeleteAddressResponse deleteAddressResponse=new DeleteAddressResponse();
			if(addressId!=null){
				List<Address> addresslist=addressDao.listAddressByAddressId(addressId);
				if(addresslist!=null && !addresslist.isEmpty()){
					Address address=addresslist.get(0);
					addressDao.deleteAddress(address);
					deleteAddressResponse.setStatus("success");
				}else{
					deleteAddressResponse.setStatus("failure");
					deleteAddressResponse.setErrorDesc("address does not exist");
					deleteAddressResponse.setErrorCode("CMSERR003");
				}
		}else{
			deleteAddressResponse.setStatus("failure");
			deleteAddressResponse.setErrorDesc("Please provide address");
			deleteAddressResponse.setErrorCode("CMSERR003");
		}
		return deleteAddressResponse;
	}
}
