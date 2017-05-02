/**
 * 
 */
package com.vmware.vra.cms.service.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vra.cms.dao.model.Address;
import com.vmware.vra.cms.dao.model.Customer;
import com.vmware.vra.cms.dao.model.User;
import com.vmware.vra.cms.dao.repositories.AddressDaoIFace;
import com.vmware.vra.cms.dao.repositories.CustomerDaoIFace;
import com.vmware.vra.cms.dao.repositories.UserDaoIFace;
import com.vmware.vra.cms.service.component.BusCustomerIFace;
import com.vmware.vra.cms.service.component.vo.AddressBVO;
import com.vmware.vra.cms.service.component.vo.CreateCustomerResponse;
import com.vmware.vra.cms.service.component.vo.CustomerBVO;
import com.vmware.vra.cms.service.component.vo.DeleteCustomerResponse;
import com.vmware.vra.cms.service.component.vo.ListCustomerBVO;
import com.vmware.vra.cms.service.component.vo.UpdateCustomerResponse;
import com.vmware.vra.cms.service.component.vo.UserBVO;

/**
 * @author RamS
 *
 */
@Component
public class BusCustomerImpl implements BusCustomerIFace {


	private static final Logger LOGGER = LoggerFactory.getLogger(BusCustomerImpl.class);
	@Autowired
	private CustomerDaoIFace customerDao;
	
	@Autowired
	private AddressDaoIFace addressDao;
	
	@Autowired
	private UserDaoIFace userDao;

	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public ListCustomerBVO listCustomers() {
		LOGGER.debug("entering into listCustomers()");
		ListCustomerBVO listCustomerBVO=new ListCustomerBVO();
		try{
			List<Customer> customerList=customerDao.listCustomers();
			if(customerList!=null && !customerList.isEmpty()){
				LOGGER.debug("Size of listCustomers()={}",customerList.size());
				List<CustomerBVO> customerBVOList=new ArrayList<CustomerBVO>();
				for(Customer customer:customerList){
					CustomerBVO customerBVO=new CustomerBVO();
					customerBVO.setCustomerId(customer.getCustomerId());
					customerBVO.setCustomerFirstName(customer.getCustomerFirstName());
					customerBVO.setCustomerLastName(customer.getCustomerLastName());
					if(customer.getCustomerStatus()!=null && customer.getCustomerStatus()){
						customerBVO.setCustomerStatus("active");
					}else{
						customerBVO.setCustomerStatus("inactive");
					}
					customerBVOList.add(customerBVO);
				}
				listCustomerBVO.setCustomerList(customerBVOList);
				listCustomerBVO.setStatus("success");
			}else{
				listCustomerBVO.setStatus("success");
			}
		}catch(DataAccessException exe){
			listCustomerBVO.setStatus("failure");
			listCustomerBVO.setErrorDesc("Error ocurred while accessing Database");
			listCustomerBVO.setErrorCode("CMSCUST001");
		}
		LOGGER.debug("exiting from listCustomers()");
		return listCustomerBVO;
	}

	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public CreateCustomerResponse addCustomer(CustomerBVO customerBVO) {
		LOGGER.debug("entering into addCustomer() with customerBVO={} ",customerBVO.toString());
		CreateCustomerResponse createCustomerResponse=new CreateCustomerResponse();
		try{
			if(customerBVO!=null){
				Customer customer=new Customer();
				customer.setCustomerId(customerBVO.getCustomerId());
				customer.setCustomerFirstName(customerBVO.getCustomerFirstName());
				customer.setCustomerLastName(customerBVO.getCustomerLastName());
				if(customerBVO.getCustomerStatus().equalsIgnoreCase("active")){
					customer.setCustomerStatus(true);
				}else{
					customer.setCustomerStatus(false);
				}

				customer=customerDao.saveorupdate(customer);

				if(customerBVO.getAddress()!=null){
					AddressBVO addresslistBVO = customerBVO.getAddress();
					Address address =new Address();
					if(addresslistBVO.getAddressFirstLine()!=null){
						address.setAddressFirstLine(addresslistBVO.getAddressFirstLine());
					}

					if(addresslistBVO.getAddressSecondLine()!=null){
						address.setAddressSecondLine(addresslistBVO.getAddressSecondLine());
					}

					if(addresslistBVO.getCity()!=null){
						address.setCity(addresslistBVO.getCity());
					}
					if(addresslistBVO.getState()!=null){
						address.setState(addresslistBVO.getState());
					}

					if(addresslistBVO.getCountry()!=null){
						address.setCountry(addresslistBVO.getCountry());
					}

					if(addresslistBVO.getZipCode()!=null){
						address.setZipCode(addresslistBVO.getZipCode());
					}

					address.setCustomer(customer);
					address=addressDao.saveorupdate(address);
				}
				
				if(customerBVO.getUser()!=null){
					UserBVO userBVO=customerBVO.getUser();
					User user=new User();
					
					if(userBVO.getUserName()!=null){
						user.setUserName(userBVO.getUserName());
					
					}
					
					if(userBVO.getPassword()!=null){
						user.setPassword(userBVO.getPassword());
					}
					
					user.setCustomer(customer);
					user=userDao.saveorupdate(user);
				}
				
				if(customer.getCustomerId()!=null){
					customerBVO.setCustomerId(customer.getCustomerId());
				}
				createCustomerResponse.setCustomerBVO(customerBVO);
				createCustomerResponse.setStatus("success");
				LOGGER.debug("customer={}",customer.toString());
			}
		}catch(DataAccessException exe){
			LOGGER.error("Error={}",exe);
			createCustomerResponse.setStatus("failure");
			createCustomerResponse.setErrorCode("CMSCUST002");
			createCustomerResponse.setErrorDesc("error ouccured while adding customer");
		}
		LOGGER.debug("exiting from addCustomer() with customerBVO={} ",customerBVO.toString());
		return createCustomerResponse;
	}


	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public UpdateCustomerResponse updateCustomer(CustomerBVO customerBVO) {
		LOGGER.debug("entering into addCustomer() with customerBVO={} ",customerBVO.toString());
		UpdateCustomerResponse createCustomerResponse=new UpdateCustomerResponse();
		try{
			if(customerBVO!=null){
				Customer customer=customerDao.getCustomerbyCustId(customerBVO.getCustomerId());
				if(customer!=null){
					if(customerBVO.getCustomerStatus().equalsIgnoreCase("active")
							||customerBVO.getCustomerStatus().equalsIgnoreCase("inactive")){
						customer.setCustomerId(customerBVO.getCustomerId());
						customer.setCustomerFirstName(customerBVO.getCustomerFirstName());
						customer.setCustomerLastName(customerBVO.getCustomerLastName());
						if(customerBVO.getCustomerStatus().equalsIgnoreCase("active")){
							customer.setCustomerStatus(true);
						}else if(customerBVO.getCustomerStatus().equalsIgnoreCase("inactive")){
							customer.setCustomerStatus(false);
						}
						customer=customerDao.saveorupdate(customer);
						if(customerBVO.getAddress()!=null){
							AddressBVO addresslistBVO = customerBVO.getAddress();
							Address address =new Address();
							if(addresslistBVO.getAddressId()!=null){
								address.setAddressId(addresslistBVO.getAddressId());
							}

							if(addresslistBVO.getAddressFirstLine()!=null){
								address.setAddressFirstLine(addresslistBVO.getAddressFirstLine());
							}

							if(addresslistBVO.getAddressSecondLine()!=null){
								address.setAddressFirstLine(addresslistBVO.getAddressSecondLine());
							}

							if(addresslistBVO.getCity()!=null){
								address.setCity(addresslistBVO.getCity());
							}
							if(addresslistBVO.getState()!=null){
								address.setState(addresslistBVO.getState());
							}

							if(addresslistBVO.getCountry()!=null){
								address.setCountry(addresslistBVO.getCountry());
							}

							if(addresslistBVO.getZipCode()!=null){
								address.setZipCode(addresslistBVO.getZipCode());
							}

							address.setCustomer(customer);
							address=addressDao.saveorupdate(address);
						}
						
						if(customerBVO.getUser()!=null){
							UserBVO userBVO=customerBVO.getUser();
							User user=new User();
							if(userBVO.getUserId()!=null){
								user.setUserId(userBVO.getUserId());
							}
							
							if(userBVO.getUserName()!=null){
								user.setUserName(userBVO.getUserName());
							
							}
							
							if(userBVO.getPassword()!=null){
								user.setPassword(userBVO.getPassword());
							}
							
							user.setCustomer(customer);
							user=userDao.saveorupdate(user);
						}
					
						if(customer.getCustomerId()!=null){
							customerBVO.setCustomerId(customer.getCustomerId());
						}
						createCustomerResponse.setCustomerBVO(customerBVO);
						createCustomerResponse.setStatus("success");
						LOGGER.debug("customer={}",customer.toString());
					}else{
						createCustomerResponse.setStatus("failure");
						createCustomerResponse.setErrorDesc("not a valid status");
						createCustomerResponse.setErrorCode("CMSCUST003");
					}
				}else{
					createCustomerResponse.setStatus("failure");
					createCustomerResponse.setErrorDesc("customer does not exist");
					createCustomerResponse.setErrorCode("CMSCUST004");
				}
			}
		}catch(DataAccessException exe){
			LOGGER.error("Error={}",exe);
			createCustomerResponse.setStatus("failure");
			createCustomerResponse.setErrorCode("CMSCUST005");
			createCustomerResponse.setErrorDesc("error ouccured while adding customer");
		}
		LOGGER.debug("exiting from addCustomer() with customerBVO={} ",customerBVO.toString());
		return createCustomerResponse;
	}



	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public ListCustomerBVO getCustomer(Integer custId) {
		LOGGER.debug("entering into getCustomer() with custId={} ",custId);
		ListCustomerBVO listCustomerBVO=new ListCustomerBVO();
		Customer customer=customerDao.getCustomerbyCustId(custId);
		List <CustomerBVO> listCustomer=new ArrayList <CustomerBVO>();
		if(customer!=null){
			CustomerBVO customerBVO=new CustomerBVO();
			customerBVO.setCustomerId(customer.getCustomerId());
			customerBVO.setCustomerFirstName(customer.getCustomerFirstName());
			customerBVO.setCustomerLastName(customer.getCustomerLastName());
			if(customer.getCustomerStatus()){
				customerBVO.setCustomerStatus("active");
			}else{
				customerBVO.setCustomerStatus("inactive");
			}
			listCustomer.add(customerBVO);
			listCustomerBVO.setCustomerList(listCustomer);
			listCustomerBVO.setStatus("success");
		}else{
			listCustomerBVO.setStatus("success");
		}
		LOGGER.debug("entering into getCustomer()");
		return listCustomerBVO;
	}

	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public DeleteCustomerResponse deleteCustomer(Integer custId) {
		LOGGER.debug("entering into addCustomer() with customerBVO={} ",custId);
		DeleteCustomerResponse createCustomerResponse=new DeleteCustomerResponse();
		try{
			if(custId!=null){
				Customer customer=customerDao.getCustomerbyCustId(custId);
				if(customer!=null){
					customerDao.deletecustomer(customer);
					createCustomerResponse.setStatus("success");
				}else{
					createCustomerResponse.setStatus("failure");
					createCustomerResponse.setErrorDesc("customer does not exist");
					createCustomerResponse.setErrorCode("CMSCUST004");
				}
			}
		}catch(Exception exe){
			LOGGER.error("Error={}",exe);
			createCustomerResponse.setStatus("failure");
			createCustomerResponse.setErrorCode("CMSCUST005");
			createCustomerResponse.setErrorDesc("error ouccured while deleting customer");
		}
		LOGGER.debug("exiting from addCustomer() with customerBVO={} ",createCustomerResponse.toString());
		return createCustomerResponse;
	}

}
