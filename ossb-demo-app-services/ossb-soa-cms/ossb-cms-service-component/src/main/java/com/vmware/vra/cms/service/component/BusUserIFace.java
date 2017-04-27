/**
 * 
 */
package com.vmware.vra.cms.service.component;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vra.cms.service.component.vo.CreateUserResponse;
import com.vmware.vra.cms.service.component.vo.DeleteUserResponse;
import com.vmware.vra.cms.service.component.vo.ListUserBVO;
import com.vmware.vra.cms.service.component.vo.UpdateUserResponse;
import com.vmware.vra.cms.service.component.vo.UserBVO;

/**
 * @author RamS
 *
 */


public interface BusUserIFace {
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public UserBVO getUserByUserName(String userName);
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public ListUserBVO listUsers();
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public CreateUserResponse addUser(UserBVO userBVO) throws Exception;
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public UserBVO validateUser(String userName,String password);
	
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public UpdateUserResponse updateUser(UserBVO userBVO) throws Exception;

	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public DeleteUserResponse deleteUser(String userName);
}
