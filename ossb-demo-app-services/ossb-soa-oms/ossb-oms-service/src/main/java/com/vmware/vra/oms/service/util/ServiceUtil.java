package com.vmware.vra.oms.service.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ServiceUtil {


	public static HttpHeaders getHTTPHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		//httpHeaders.set("Content-Type", "application/xml");
		return httpHeaders;
	}

	public static <T> ResponseEntity<T> getResponseEntity(T object) {
		ResponseEntity<T> responseEntity = new ResponseEntity<T>(object, ServiceUtil.getHTTPHeaders(), HttpStatus.OK);
		return responseEntity;
	}
	
}
