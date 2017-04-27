/**
 * 
 */
package com.vmware.vra.cms.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vmware.vra.cms.service.component.vo.CreateUserResponse;
import com.vmware.vra.cms.service.component.vo.DeleteUserResponse;
import com.vmware.vra.cms.service.component.vo.ListUserBVO;
import com.vmware.vra.cms.service.component.vo.UpdateUserResponse;
import com.vmware.vra.cms.service.component.vo.UserBVO;

/**
 * @author RamS
 *
 */

@Controller
public interface UserServiceIFace {

	
	@RequestMapping(value="/userservice"  ,method = RequestMethod.GET)
	public ResponseEntity<ListUserBVO> listUsers();
	
	@RequestMapping(value="/userservice", method = RequestMethod.PUT)
	public ResponseEntity<CreateUserResponse> addUser(HttpServletRequest req,
			@RequestBody UserBVO userBVO) throws Exception;
	
	@RequestMapping(value="/userservice", method = RequestMethod.POST)
	public ResponseEntity<UpdateUserResponse> updateUser(HttpServletRequest req,
			@RequestBody UserBVO userBVO) throws Exception;
	
	@RequestMapping(value="/userservice/{username}" ,method = RequestMethod.GET)
	public ResponseEntity<UserBVO> listUser(@PathVariable("username") String username);
	
	@RequestMapping(value = "/userservice/{username}", method = RequestMethod.DELETE)
	public ResponseEntity<DeleteUserResponse> deleteUser(@PathVariable("username") String username);
	
	@RequestMapping(value = "/userservice/{username}/validate", method = RequestMethod.GET)
	public ResponseEntity<UserBVO> validateUser(HttpServletRequest req,@PathVariable("username") String username);
	
	
}
