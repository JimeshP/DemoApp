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
public interface CustomerServiceIFace {
	
	
	@RequestMapping(value="/customerservice",method = RequestMethod.GET)
	public ResponseEntity<ListCustomerBVO>  listCustomers();
	
	@RequestMapping(value="/customerservice",method = RequestMethod.PUT)
	public ResponseEntity< CreateCustomerResponse> addCustomer(HttpServletRequest req,
			@RequestBody CustomerBVO  customerBVO );
	
	@RequestMapping(value="/customerservice",method = RequestMethod.POST)
	public ResponseEntity< UpdateCustomerResponse> updateCustomer(HttpServletRequest req,
			@RequestBody CustomerBVO  customerBVO);
	
	@RequestMapping(value="/customerservice/{customerid}",method = RequestMethod.GET)
	public ResponseEntity<ListCustomerBVO>  listCustomer(@PathVariable("customerid") Integer customerid);
	
	@RequestMapping(value="/customerservice/{customerid}",method = RequestMethod.DELETE)
	public ResponseEntity<DeleteCustomerResponse>  deleteCustomer(@PathVariable("customerid") Integer customerid);

}
