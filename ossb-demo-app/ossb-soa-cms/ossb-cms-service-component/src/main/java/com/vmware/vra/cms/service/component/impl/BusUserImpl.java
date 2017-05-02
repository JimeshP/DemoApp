package com.vmware.vra.cms.service.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vra.cms.dao.model.Customer;
import com.vmware.vra.cms.dao.model.User;
import com.vmware.vra.cms.dao.repositories.CustomerDaoIFace;
import com.vmware.vra.cms.dao.repositories.UserDaoIFace;
import com.vmware.vra.cms.service.component.BusUserIFace;
import com.vmware.vra.cms.service.component.vo.CreateUserResponse;
import com.vmware.vra.cms.service.component.vo.DeleteUserResponse;
import com.vmware.vra.cms.service.component.vo.ListUserBVO;
import com.vmware.vra.cms.service.component.vo.UpdateUserResponse;
import com.vmware.vra.cms.service.component.vo.UserBVO;


/**
 * @author RamS
 *
 */
@Component    
public class BusUserImpl implements BusUserIFace {

	@Autowired
	private UserDaoIFace userDao;

	@Autowired
	private CustomerDaoIFace customerDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(BusUserImpl.class);

	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public UserBVO getUserByUserName(String userName) {
		LOGGER.debug("Entring listUsers()");
		UserBVO userBVO=new UserBVO();
		try{
			User user=userDao.getUserByUserName(userName,null);
			if(user!=null){
				LOGGER.debug("User= {}",user.toString());
				userBVO=new UserBVO();
				userBVO.setUserId(user.getUserId());
				userBVO.setUserName(user.getUserName());
				userBVO.setPassword(user.getPassword());
				userBVO.setCustomerId(user.getCustomer().getCustomerId());
			} 
		}catch(Exception exe){
			LOGGER.error("execetion occured db layer={}",exe);
		}
		LOGGER.debug("Exiting listUsers() with userBVO={}",userBVO.toString());
		return userBVO;
	}

	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public ListUserBVO listUsers() {
		LOGGER.debug("Entring listUsers()");
		ListUserBVO listUserBVO=new ListUserBVO();
		try{
			List<User> userList=userDao.listUsers();

			if(userList!=null && !userList.isEmpty()){
				LOGGER.debug("listUsers() size= {}",userList.size());
				List<UserBVO> userBVOList=new ArrayList<UserBVO>();
				for(User user:userList){
					UserBVO userBVO=new UserBVO();
					userBVO.setUserId(user.getUserId());
					userBVO.setUserName(user.getUserName());
					userBVO.setPassword(user.getPassword());
					userBVO.setCustomerId(user.getCustomer().getCustomerId());
					userBVOList.add(userBVO);
				}
				listUserBVO.setUserList(userBVOList);
				listUserBVO.setStatus("success");
			} else {
				listUserBVO.setStatus("success");
			}
		}catch(Exception exe){
			listUserBVO.setStatus("failure");
			listUserBVO.setErrorDesc("Error ocurred while accessing Database");
			listUserBVO.setErrorCode("DB001");
		}
		LOGGER.debug("Exiting listUsers()");
		return listUserBVO;
	}


	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public CreateUserResponse addUser(UserBVO userBVO) throws Exception {
		LOGGER.debug("Entring addUser()");
		CreateUserResponse createUserResponse=new CreateUserResponse();
		try{
			if(userBVO!=null ){
				User isUserExist=userDao.getUserByUserName(userBVO.getUserName(),null);
				if(isUserExist!=null){
					createUserResponse.setStatus("failure");
					createUserResponse.setErrorCode("Duplicate-userName");
					createUserResponse.setErrorDesc("user name is already exist");
				}else{
					User user=createUser(userBVO);
					user=userDao.saveorupdate(user);
					if(user!=null){
						userBVO=new UserBVO();
						userBVO.setUserId(user.getUserId());
						userBVO.setUserName(user.getUserName());
						userBVO.setPassword(user.getPassword());
						userBVO.setCustomerId(user.getCustomer().getCustomerId());
						createUserResponse.setUserBVO(userBVO);
						createUserResponse.setStatus("success");
					}
				}
			}
		}catch(Exception exe){
			LOGGER.error("Execetion={}",exe);
			createUserResponse.setStatus("failure");
			createUserResponse.setErrorCode("CMSERR003");
			createUserResponse.setErrorDesc("error occureed while adding user");
		}
		LOGGER.debug("Exiting addUser() with userBVO={}",userBVO.toString());
		return createUserResponse;
	}

	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public UserBVO validateUser(String userName, String password) {

		LOGGER.debug("Entring validateUser()  with userName={} and password={}",userName,password);
		UserBVO userBVO=new UserBVO();
		try{
			User user=userDao.getUserByUserName(userName,password);
			if(user!=null){
				LOGGER.debug("User= {}",user.toString());
				userBVO=new UserBVO();
				userBVO.setUserId(user.getUserId());
				userBVO.setUserName(user.getUserName());
				userBVO.setPassword(user.getPassword());
				userBVO.setCustomerId(user.getCustomer().getCustomerId());
			} 
		}catch(Exception exe){
			LOGGER.error("execetion occured db layer={}",exe);
		}
		LOGGER.debug("Exiting validateUser() with userBVO={}",userBVO.toString());
		return userBVO;

	}

	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public UpdateUserResponse updateUser(UserBVO userBVO) throws Exception {
		LOGGER.debug("Entring updateUser() with userBVO={}",userBVO.toString());
		UpdateUserResponse updateUserResponse=new UpdateUserResponse();
		try{
			if(userBVO!=null ){
				User user=userDao.getUserbyUserId(userBVO.getUserId());
				if(user!=null){
					user=createUser(userBVO);
					user=userDao.saveorupdate(user);
					if(user!=null){
						userBVO=new UserBVO();
						userBVO.setUserId(user.getUserId());
						userBVO.setUserName(user.getUserName());
						userBVO.setPassword(user.getPassword());
						userBVO.setCustomerId(user.getCustomer().getCustomerId());
						updateUserResponse.setUserBVO(userBVO);
						updateUserResponse.setStatus("success");
					}
				}else{
					updateUserResponse.setStatus("failure");
					updateUserResponse.setErrorDesc("user does not exist");
					updateUserResponse.setErrorCode("CMSDB001");
				}
			}
		}catch(Exception exe){
			updateUserResponse.setStatus("failure");
			updateUserResponse.setErrorDesc("Unknow error occure.unable to update user");
			updateUserResponse.setErrorCode("CMSDB002");
		}
		LOGGER.debug("Exiting addUser() with userBVO={}",userBVO.toString());
		return updateUserResponse;
	}


	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=false)
	public  User createUser(UserBVO userBVO) throws Exception{
		User user=null;
		if(userBVO!=null && userBVO.getCustomerId()!=null){
			Customer customer=customerDao.getCustomerbyCustId(userBVO.getCustomerId());
			user=new User();
			user.setUserId(userBVO.getUserId());
			if(userBVO.getUserName()!=null){
				user.setUserName(userBVO.getUserName());
			}else{
				throw new Exception();
			}
			user.setPassword(userBVO.getPassword());
			user.setCustomer(customer);
		}
		return user;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public DeleteUserResponse deleteUser(String userName) {
		LOGGER.debug("Entring deleteUser()");
		DeleteUserResponse deleteUserResponse=new DeleteUserResponse();
		try{
			if(userName!=null){
				User user=userDao.getUserByUserName(userName,null);
				if(user!=null){
					userDao.deleteUser(user);
					deleteUserResponse.setStatus("success");
				}else{
					deleteUserResponse.setStatus("failure");
					deleteUserResponse.setErrorDesc("user does not exist");
					deleteUserResponse.setErrorCode("CMSERR001");
				}
			}
		}catch(Exception exe){
			LOGGER.error("error occured while deleting user= {}",exe);
			deleteUserResponse.setStatus("failure");
			deleteUserResponse.setErrorDesc("erro occured while deleting user");
			deleteUserResponse.setErrorCode("CMSERR002");
		}
		LOGGER.debug("Exiting addUser() with deleteUserResponse={}",deleteUserResponse.toString());
		return deleteUserResponse;
	}
}
