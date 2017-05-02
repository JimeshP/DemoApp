/**
 * 
 */
package com.vmware.vra.cms.dao.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vra.cms.dao.model.User;

/**
 * @author RamS
 *
 */
public interface UserDaoIFace {

	
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=true)
	public List<User> listUsers()throws DataAccessException;
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=true)
	public User getUserbyUserId(Integer userId)throws DataAccessException;

	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=true)
	public User getUserByUserName(String userName,String password) throws DataAccessException;
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=false)
	public User saveorupdate(User user) throws DataAccessException;
	

	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteUser(User user) throws DataAccessException;
}
