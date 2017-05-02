/**
 * 
 */
package com.vmware.vra.cms.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vmware.vra.cms.service.component.vo.AddressBVO;
import com.vmware.vra.cms.service.component.vo.CreateAddressResponse;
import com.vmware.vra.cms.service.component.vo.DeleteAddressResponse;
import com.vmware.vra.cms.service.component.vo.ListAddressResponse;

/**
 * @author RamS
 *
 */

public interface AddressServiceIFace {

	@RequestMapping(value="/addressservice",method = RequestMethod.GET)
	public ResponseEntity<ListAddressResponse>  listAddress() throws Exception;

	@RequestMapping(value="/addressservice",method = RequestMethod.PUT)
	public ResponseEntity<CreateAddressResponse>  addAddress(HttpServletRequest req,
			@RequestBody AddressBVO addressBVO) throws Exception;

	@RequestMapping(value="/addressservice",method = RequestMethod.POST)
	public ResponseEntity<CreateAddressResponse>  updateAddress(HttpServletRequest req,
			@RequestBody AddressBVO addressBVO) throws Exception;

	@RequestMapping(value="/addressservice/customer/{customerid}",method = RequestMethod.GET)
	public ResponseEntity<ListAddressResponse>  listAddressBycustomerId(@PathVariable("customerid") Integer customerid) 
			throws Exception;

	@RequestMapping(value="/addressservice/{addressid}",method = RequestMethod.GET)
	public ResponseEntity<ListAddressResponse>  listAddressByAddressId(@PathVariable("addressid") Integer addressid) 
			throws Exception;
	
	@RequestMapping(value="/addressservice/{addressid}",method = RequestMethod.DELETE)
	public ResponseEntity<DeleteAddressResponse>  deleteAddress(@PathVariable("addressid") Integer addressid) 
			throws Exception;

}
