package com.vmware.vra.oms.dao.constants;

public interface ApplicationConstants {
	
	public interface ErrorCode {
		String GENERAL_EXP_CODE = "OMS_DAO_999";
		String GET_ALL_ORDER_EXP_CODE = "OMS_DAO_001";
		String GET_ORDER_EXP_CODE = "OMS_DAO_002";
		String GET_ALL_ORDER_PER_CUST_EXP_CODE = "OMS_DAO_003";
		String GET_SAVE_UPDATE_ORD_EXP_CODE = "OMS_DAO_004";
		String GET_DELETE_ORDER_EXP_CODE = "OMS_DAO_005";
		
	}

	public interface ErrorMessage {
		String DATAACCESSEXP_MSG = "Excetion at DAO layer due to Invalid Data";
		String GENERAL_EXP_MSG = "Unknown error at DAO layer";
		
	}
}
