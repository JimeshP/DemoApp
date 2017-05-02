package com.vmware.vra.cms.service.impl;



import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vmware.vra.cms.service.UserServiceIFace;
import com.vmware.vra.cms.service.component.BusUserIFace;
import com.vmware.vra.cms.service.component.vo.CreateUserResponse;
import com.vmware.vra.cms.service.component.vo.DeleteUserResponse;
import com.vmware.vra.cms.service.component.vo.ListUserBVO;
import com.vmware.vra.cms.service.component.vo.UpdateUserResponse;
import com.vmware.vra.cms.service.component.vo.UserBVO;

@Controller
public class UserServiceImpl  implements UserServiceIFace{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private BusUserIFace busUser;

	@Override
	@RequestMapping(value="/userservice"  ,method = RequestMethod.GET)
	public ResponseEntity<ListUserBVO> listUsers() {
		HttpHeaders responseHeaders = new HttpHeaders();
		ListUserBVO listUserBVO=busUser.listUsers();
		return new ResponseEntity(listUserBVO, responseHeaders, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value="/userservice/{username}" ,method = RequestMethod.GET)
	public ResponseEntity<UserBVO> listUser(@PathVariable("username") String username) {
		HttpHeaders responseHeaders = new HttpHeaders();
		UserBVO userBVO=busUser.getUserByUserName(username);
		return new ResponseEntity(userBVO, responseHeaders, HttpStatus.OK);
	}


	@Override
	@RequestMapping(value="/userservice", method = RequestMethod.PUT)
	public ResponseEntity<CreateUserResponse> addUser(HttpServletRequest req,
			@RequestBody UserBVO userBVO) throws Exception {
		HttpHeaders responseHeaders = new HttpHeaders();
		CreateUserResponse createUserResponse=busUser.addUser(userBVO);
		return new ResponseEntity(createUserResponse, responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value="/userservice", method = RequestMethod.POST)
	public ResponseEntity<UpdateUserResponse> updateUser(HttpServletRequest req,
			@RequestBody UserBVO userBVO) throws Exception{
		HttpHeaders responseHeaders = new HttpHeaders();
		UpdateUserResponse createUserResponse=busUser.updateUser(userBVO);
		return new ResponseEntity(createUserResponse, responseHeaders, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/userservice/{username}/validate", method = RequestMethod.GET)
	public ResponseEntity<UserBVO> validateUser(HttpServletRequest req,
			@PathVariable("username") String username) {
		HttpHeaders responseHeaders = new HttpHeaders();
		UserBVO userBVO=new UserBVO();
		if(req.getHeader("authkey")!=null){
			String password=req.getHeader("authkey");
			 userBVO=busUser.validateUser(username,password);
		}
		return new ResponseEntity(userBVO, responseHeaders, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/userservice/{username}", method = RequestMethod.DELETE)
	public ResponseEntity<DeleteUserResponse> deleteUser(@PathVariable("username") String username) {
		HttpHeaders responseHeaders = new HttpHeaders();
		DeleteUserResponse deleteUserResponse=busUser.deleteUser(username);
		return new ResponseEntity(deleteUserResponse, responseHeaders, HttpStatus.OK);
	}
}
