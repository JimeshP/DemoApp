/**
 * 
 */
package com.vmware.vra.cms.dao.repositories.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vra.cms.dao.model.User;
import com.vmware.vra.cms.dao.repositories.UserDaoIFace;


/**
 * @author RamS
 *
 */

@Repository
public class UserDaoImpl  extends HibernateDaoSupport implements UserDaoIFace{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	public void setMySessionFactory(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=true)
	public List<User> listUsers() throws DataAccessException {
		LOGGER.debug("Entring listUsers()");
		List<User> userList=null;
		userList=this.getHibernateTemplate().loadAll(User.class);
		LOGGER.debug("Exiting listUsers()");
		return userList;
	}
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=true)
	public User getUserbyUserId(Integer userId) throws DataAccessException{
		LOGGER.debug("Entring getUserbyUserId() with userId={}",userId);
		User user=null;
		DetachedCriteria pagingCriteria = DetachedCriteria.forClass(User.class);
		if(userId!=null){
			pagingCriteria.add(Restrictions.eq("userId", userId));
		}

		List<User> userlist= (List<User>) this.getHibernateTemplate().findByCriteria(pagingCriteria);
		if(userlist!=null && !userlist.isEmpty()){
			LOGGER.debug("exiting userlist()={}",userlist.toString());
			user=userlist.get(0);
			LOGGER.debug("exiting getUserByUserName()={}",user.toString());
		}
		LOGGER.debug("Exiting getUserbyUserId()");
		return user;
	}

	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=false)
	public User saveorupdate(User user) throws DataAccessException{
		LOGGER.debug("Entring saveorupdate() with user={}",user.toString());
		this.getHibernateTemplate().saveOrUpdate(user);
		LOGGER.debug("Exiting saveorupdate()");
		return user;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteUser(User user) throws DataAccessException{
		LOGGER.debug("Entring deleteUser() with user={}",user.toString());
		this.getHibernateTemplate().delete(user);
		LOGGER.debug("Exiting deleteUser()");
		
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,readOnly=true)
	public User getUserByUserName(String userName,String password)throws DataAccessException {
		LOGGER.debug("Entring getUserByUserName() with userName={}",userName);
		User user=null;
		DetachedCriteria pagingCriteria = DetachedCriteria.forClass(User.class);
		if(userName!=null){
			pagingCriteria.add(Restrictions.eq("userName", userName));
		}
		if(password!=null){
			pagingCriteria.add(Restrictions.eq("password", password));
		}

		List<User> userlist= (List<User>) this.getHibernateTemplate().findByCriteria(pagingCriteria);
		if(userlist!=null && !userlist.isEmpty()){
			LOGGER.debug("exiting userlist()={}",userlist.toString());
			user=userlist.get(0);
			LOGGER.debug("exiting getUserByUserName()={}",user.toString());
		}
		LOGGER.debug("Exiting getUserByUserName()");
		return user;
	}
}
