package com.vmware.vra.oms.service.constants;

public interface ApplicationConstants {

	public static final String OPERATOR_COMMAND_SLASH = "/";
	public static final String URI_PATH_VARIABLE_START = "{";
	public static final String URI_PATH_VARIABLE_END = "}";
	
	public static final String ORDER_SERVICE = "orderservice";
	public static final String ORDER_ID = "orderId";
	public static final String ORDER = "order";
	public static final String CUSTOMER_ID = "customerId";
	public static final String CUSTOMER = "customer";
	public static final String PROCESS_QUEUE_ORDER = "processorder";
	
	public interface ErrorCode {
		String GENERAL_EXP_CODE = "OMS_SER_999";
		String GET_ALL_ORDER_EXP_CODE = "OMS_SER_001";
		String GET_ORDER_EXP_CODE = "OMS_SER_002";
		String GET_ALL_ORDER_PER_CUST_EXP_CODE = "OMS_SER_003";
		String GET_SAVE_UPDATE_ORD_EXP_CODE = "OMS_SER_004";
		String GET_DELETE_ORDER_EXP_CODE = "OMS_SER_005";
	}
	
	public interface Message {
		String GENERAL_EXP_MSG = "Unknown error at Service layer";
	}
	
}
