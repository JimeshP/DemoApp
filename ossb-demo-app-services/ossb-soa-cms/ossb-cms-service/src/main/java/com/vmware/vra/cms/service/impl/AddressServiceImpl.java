/**
 * 
 */
package com.vmware.vra.cms.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vmware.vra.cms.service.AddressServiceIFace;
import com.vmware.vra.cms.service.component.BusAddressIFace;
import com.vmware.vra.cms.service.component.vo.AddressBVO;
import com.vmware.vra.cms.service.component.vo.CreateAddressResponse;
import com.vmware.vra.cms.service.component.vo.DeleteAddressResponse;
import com.vmware.vra.cms.service.component.vo.ListAddressResponse;

/**
 * @author RamS
 *
 */
@Controller
public class AddressServiceImpl implements AddressServiceIFace {

	@Autowired
	private BusAddressIFace busAddress;

	@Override
	@RequestMapping(value = "/addressservice", method = RequestMethod.GET)
	public ResponseEntity<ListAddressResponse> listAddress() throws Exception {
		HttpHeaders responseHeaders = new HttpHeaders();
		ListAddressResponse	listAddressResponse=busAddress.listAddress();
		return new ResponseEntity(listAddressResponse, responseHeaders, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/addressservice", method = RequestMethod.PUT)
	public ResponseEntity<CreateAddressResponse> addAddress(HttpServletRequest req,
			@RequestBody AddressBVO addressBVO) throws Exception{
		HttpHeaders responseHeaders = new HttpHeaders();
		CreateAddressResponse createAddressResponse=busAddress.addAddress(addressBVO);
		return new ResponseEntity(createAddressResponse, responseHeaders, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/addressservice", method = RequestMethod.POST)
	public ResponseEntity<CreateAddressResponse> updateAddress(HttpServletRequest req,
			@RequestBody AddressBVO addressBVO) throws Exception {
		HttpHeaders responseHeaders = new HttpHeaders();
		CreateAddressResponse createAddressResponse=busAddress.addAddress(addressBVO);
		return new ResponseEntity(createAddressResponse, responseHeaders, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/addressservice/customer/{customerid}", method = RequestMethod.GET)
	public ResponseEntity<ListAddressResponse> listAddressBycustomerId(
			@PathVariable("customerid") Integer customerid) throws Exception {
		HttpHeaders responseHeaders = new HttpHeaders();
		ListAddressResponse	listAddressResponse=busAddress.listAddressByCustomerId(customerid);
		return new ResponseEntity(listAddressResponse, responseHeaders, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/addressservice/{addressid}", method = RequestMethod.GET)
	public ResponseEntity<ListAddressResponse> listAddressByAddressId(
			@PathVariable("addressid") Integer addressid) throws Exception {
		HttpHeaders responseHeaders = new HttpHeaders();
		ListAddressResponse	listAddressResponse=busAddress.listAddressByAddressId(addressid);
		return new ResponseEntity(listAddressResponse, responseHeaders, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/addressservice/{addressid}", method = RequestMethod.DELETE)
	public ResponseEntity<DeleteAddressResponse> deleteAddress(
			@PathVariable("addressid") Integer addressid) throws Exception {
			HttpHeaders responseHeaders = new HttpHeaders();
			DeleteAddressResponse deleteAddressResponse=busAddress.deleteAddress(addressid);
		return new ResponseEntity(deleteAddressResponse, responseHeaders, HttpStatus.OK);
	}
	
}
