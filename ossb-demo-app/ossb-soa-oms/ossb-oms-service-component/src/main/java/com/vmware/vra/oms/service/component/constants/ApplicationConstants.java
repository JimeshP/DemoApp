package com.vmware.vra.oms.service.component.constants;

public interface ApplicationConstants {

	public static final String OPERATION_TYPE_CREATE = "createOrder";
	public static final String OPERATION_TYPE_UPDATE = "updateOrder";
	
	public interface ErrorCode {
		String GET_ALL_ORDER_EXP_CODE = "OMS_BUS_001";
		String GET_ORDER_EXP_CODE = "OMS_BUS_002";
		String GET_ALL_ORDER_PER_CUST_EXP_CODE = "OMS_BUS_003";
		String GET_SAVE_UPDATE_ORD_EXP_CODE = "OMS_BUS_004";
		String GET_DELETE_ORDER_EXP_CODE = "OMS_BUS_005";
	}

	public interface ErrorMessage {
		//String DATAACCESSEXP_MSG = "Excetion at BUS layer due to Invalid Data";
		String GENERAL_EXP_MSG = "Unknown error at BUS layer";
	}
	
	
	public interface Message {
		String NO_DATA = "No data";
		String NO_DATA_FOR_ORDER = "No order found for id ";
		String NO_ORDERS_FOR_CUSTOMER = "No order details found for customer id ";
		String ORDER_DELETE_SUCCESS = "Order deleted successfully. OrderId: ";
		String ORDER_DELETE_FAILURE = "Unable to delete the order. OrderId: ";
		
	}
}
