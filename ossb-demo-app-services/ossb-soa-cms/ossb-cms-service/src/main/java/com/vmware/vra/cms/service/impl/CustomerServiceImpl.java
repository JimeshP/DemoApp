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

import com.vmware.vra.cms.service.CustomerServiceIFace;
import com.vmware.vra.cms.service.component.BusCustomerIFace;
import com.vmware.vra.cms.service.component.vo.CreateCustomerResponse;
import com.vmware.vra.cms.service.component.vo.CustomerBVO;
import com.vmware.vra.cms.service.component.vo.DeleteCustomerResponse;
import com.vmware.vra.cms.service.component.vo.ListCustomerBVO;
import com.vmware.vra.cms.service.component.vo.UpdateCustomerResponse;

/**
 * @author RamS
 *
 */
@Controller
public class CustomerServiceImpl implements CustomerServiceIFace{

	@Autowired
	private BusCustomerIFace busCustomer;


	@Override
	@RequestMapping(value="/customerservice",method = RequestMethod.GET)
	public ResponseEntity<ListCustomerBVO> listCustomers() {
		HttpHeaders responseHeaders = new HttpHeaders();
		ListCustomerBVO	listCustomerBVO=busCustomer.listCustomers();
		return new ResponseEntity(listCustomerBVO, responseHeaders, HttpStatus.OK);
	}


	@Override
	@RequestMapping(value="/customerservice",method = RequestMethod.PUT)
	public ResponseEntity< CreateCustomerResponse> addCustomer(HttpServletRequest req,
			@RequestBody CustomerBVO  customerBVO ) {
		HttpHeaders responseHeaders = new HttpHeaders();
		CreateCustomerResponse customerresponse=busCustomer.addCustomer(customerBVO);
		return new ResponseEntity(customerresponse, responseHeaders, HttpStatus.ACCEPTED);
	}

	@Override
	@RequestMapping(value="/customerservice",method = RequestMethod.POST)
	public ResponseEntity< UpdateCustomerResponse> updateCustomer(HttpServletRequest req,
			@RequestBody CustomerBVO  customerBVO ){
		HttpHeaders responseHeaders = new HttpHeaders();
		UpdateCustomerResponse customerresponse=busCustomer.updateCustomer(customerBVO);
		return new ResponseEntity(customerresponse, responseHeaders, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value="/customerservice/{customerid}",method = RequestMethod.GET)
	public ResponseEntity<ListCustomerBVO>  listCustomer(@PathVariable("customerid") Integer customerid){
		HttpHeaders responseHeaders = new HttpHeaders();
		ListCustomerBVO listCustomerBVO=busCustomer.getCustomer(customerid);
		return new ResponseEntity(listCustomerBVO, responseHeaders, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value="/customerservice/{customerid}",method = RequestMethod.DELETE)
	public ResponseEntity<DeleteCustomerResponse>  deleteCustomer(@PathVariable("customerid") Integer customerid){
		HttpHeaders responseHeaders = new HttpHeaders();
		DeleteCustomerResponse customerresponse=busCustomer.deleteCustomer(customerid);
		return new ResponseEntity(customerresponse, responseHeaders, HttpStatus.ACCEPTED);
	}
}
