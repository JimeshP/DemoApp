(function() {
	
	var vraservice = function($http) {
		
		var server = location.host;
		var serverURL = 'http://' + server;
		
		
		var uservalidation = function(username, password) {
			 return  $http({
		    	  method: 'GET',
		    	  url: serverURL + '/ossb-cms-service/cms/api/userservice/' + username + '/validate',
		    	  headers: {
		    		   'authkey': password
		    		 },
		    	}).then(function(response) {
		    		return response.data;
		    	  }); 
		};
		
		var registerUser = function(reqData) {
			return $http({
				method : 'PUT',
				url : serverURL + '/ossb-cms-service/cms/api/customerservice/',
				data : reqData
			}).then(function (response) {
				return response.data;
			});
		};
		
		var getCustomerOrders = function(customerid) {
			return $http.get(serverURL + "/ossb-oms-service/oms/api/orderservice/customer/" + customerid)
			.then(function(response){
				console.log(response.data.orderList);
				return response.data;
			});
		};
		
		var getProductList = function() {
			return $http.get(serverURL + "/ossb-pms-service/pms/api/productservice")
			.then(function(response){
				console.log(response.data);
				return response.data;
			});
		};
		
		var createOrder = function(reqData) {
			return $http({
					method : 'PUT',
					url : serverURL + '/ossb-oms-service/oms/api/orderservice/',
					data : reqData
				}).then(function (response) {
					return response.data;
				});
		};
		
		
		var getCustomerAddress = function(customerid) {
			return $http.get(serverURL + "/ossb-cms-service/cms/api/addressservice/customer/" + customerid)
			.then(function(response){
				console.log(response.data);
				return response.data;
			});
		};
		
		var updateOrder = function() {
			return $http({
					method : 'POST',
					url : serverURL + '/ossb-oms-service/oms/api/orderservice/processorder'
				}).then(function (response) {
					return response.data;
				});
		};
		
		return {
			uservalidation: uservalidation,
			registerUser: registerUser,
			getCustomerOrders: getCustomerOrders,
			getProductList: getProductList,
			createOrder: createOrder,
			getCustomerAddress: getCustomerAddress,
			updateOrder: updateOrder
		};
	};
	
	var dataShareService = function() {
		
		var prodList = [];
		var customerid = "";
		var customerData = "";
		
		var getProdList = function() {
			return prodList;
		}
		
		var setProdList = function(newProdList) {
			prodList = newProdList;
		}
		
		var resetProdList = function() {
			prodList = [];
		}
		
		var getCustomerId = function() {
			return customerid;
		}
		
		var setCustomerId = function(customerID) {
			customerid = customerID;
		}
		
		var setCustomerInfo = function(data) {
			customerData = data;
		}
		
		var getCustomerInfo = function() {
			return customerData;
		}
		
		return {
			getProdList: getProdList,
			setProdList: setProdList,
			resetProdList: resetProdList,
			getCustomerId: getCustomerId,
			setCustomerId: setCustomerId,
			setCustomerInfo: setCustomerInfo,
			getCustomerInfo: getCustomerInfo
		};
		
	};
	
	var app = angular.module('myApp');
	app.factory("vraservice", vraservice);
	app.factory("dataShareService", dataShareService);
	
}());