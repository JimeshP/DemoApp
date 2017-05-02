var app = angular.module('myApp');
app.controller('customersCtrl',
		function($scope, vraservice, $window, $routeParams, dataShareService) {
		
		var customerid = $routeParams.customerid;
		
		var onGetOrderSuccess = function(data) {
			console.log(data);
			$scope.orderlist = data.orderList;
		};
		
		var onGetOrderFailure = function(response) {
			console.log(response);
		};
		
		vraservice.getCustomerOrders(customerid).then(onGetOrderSuccess, onGetOrderFailure);
		

					$scope.getAmount = function(orderLineBVOList) {
						console.log(orderLineBVOList);						
						var amount = 0;
						for (var i = 0; i < orderLineBVOList.length; i++) {
							var price = orderLineBVOList[i].productPriceValue;
							console.log(price);
							amount = amount + price;							
						}
						return amount;
					};

					$scope.getOrderStatus = function(statusId) {
						var retVal = '';
						if (statusId == 0) {
							retVal = 'Queued';
						} else {
							retVal = 'Completed';
						}
						return retVal;
					};

					$scope.getPaymentMethod = function(orderId) {
						var retVal = '';
						if (orderId % 2 == 0) {
							retVal = 'Manual payment';
						} else {
							retVal = 'Webpay De Transbank';
						}
						return retVal;
					}
					
					$scope.isAddStyle = function(statusId) {
						var retVal = '';
						if (statusId == 0) {
							retVal = true;
						} else {
							retVal = false;
						}
						return retVal;
					};

					$scope.buttonClicked = function() {
						$window.alert("THE BUTTON JUST GOT CLICKED!!!");
					}
				});


app.controller('checkoutController', function($scope) {
	
});


app.controller('regController', function($scope, vraservice, $window, $location) {

	$scope.loadState = function(state_id, state_index){
		
		var option_str = document.getElementById(state_id);
		option_str.length=0;	// Fixed by Julian Woods
		option_str.options[0] = new Option('Select State','');
		option_str.selectedIndex = 0;
		var state_arr = s_a[state_index].split("|");
		for (var i=0; i<state_arr.length; i++) {
			option_str.options[option_str.length] = new Option(state_arr[i],state_arr[i]);
		}
	};
	
	$scope.print_country = function(country_id){
		// given the id of the <select> tag as function argument, it inserts <option> tags
		
		var option_str = document.getElementById(country_id);
		option_str.length=0;
		option_str.options[0] = new Option('Select Country','');
		option_str.selectedIndex = 0;
		for (var i=0; i<country_arr.length; i++) {
			option_str.options[option_str.length] = new Option(country_arr[i],country_arr[i]);
		}
	};

	$scope.address ={
		    addressFirstLine: "",
		    addressSecondLine: "",
		    city: "",
		    state: "",
		    country: "",
		    zipCode: ""
		  };
		
		$scope.user = {
		    userName: "",
		    password: ""
		  },
	
	$scope.customer = { 
			customerStatus: "active",
			customerFirstName: "",
			customerLastName: "",
			address: $scope.address,
			user: $scope.user
	};
	
	var onRegisterSuccess = function(data) {
		if(data.status == "success") {
			$window.alert("User registered successfully, Please login");
			$location.path("/vrademoapp/");
		} else {
			$window.alert("Unable to register user");
		}
	};
	
	var onRegisterError = function(data) {
		$window.alert("Unable to register user");
	};
	
	$scope.registerUser = function() {
		console.log($scope.customer);
		var data = angular.toJson($scope.customer);
		console.log(data);
		vraservice.registerUser(data).then(onRegisterSuccess, onRegisterError);
	}
	
});


app.controller('prodController', function($scope, dataShareService, $location, vraservice) {
	
	$scope.customerInfo = dataShareService.getCustomerInfo();
	console.log("CUstomer Info is " + angular.toJson($scope.customerInfo));
	
	var menuclicked = function(menu) {
		console.log("Menu cliecked " + menu);
		
		if(menu == 'Decks'){
			$scope.menuNameSelect = 'Deck';
		}else{			
			$scope.menuNameSelect = menu;
		}
		$scope.menuname = menu;		
		$scope.curPage = 0;
		$scope.pageSize = 4;
	};
	
	$scope.menuclicked = menuclicked;
	var menuName = 'Skateboard';
	menuclicked(menuName);
	
		
	
	// Pagination stuffs starts
	
	 $scope.curPage = 0;
	 $scope.pageSize = 4;
	    
	 $scope.nextPage = function() {
		 $scope.curPage=$scope.curPage+1;
    	
     };
    
     $scope.previousPage = function() {
    	 $scope.curPage=$scope.curPage-1;
     };
    // Pagination stuffs end
	
	
	
	var onProductSuccess = function(data) {
		console.log(angular.toJson(data));
		console.log(data);		
		$scope.productList = data.productList;	
		
	};
	
	var onProductFailure = function(response) {
		
	};
	
	vraservice.getProductList().then(onProductSuccess, onProductFailure);
	
	
	
	$scope.addProductToCart = function(product) {
		console.log(angular.toJson(product));
		
		dataShareService.getProdList().push({
										productId: product.productId, 
										productName: product.productName, 
										productPrice:product.productPriceBVOList[0].pricePerUnit,
										productPriceId:product.productPriceBVOList[0].productPriceId,
										isProd:true
										});
		console.log(dataShareService.getProdList());
		
	};
	
});


angular.module('myApp').filter('pagination', function()
		{
		 return function(input, start)
		 {
		  if (!input || !input.length) { return; }
		  start = +start;
		  return input.slice(start);
		 };
});

app.controller('cartBarController', function($scope, dataShareService, $window, $location, vraservice) {
	$scope.cartList = dataShareService.getProdList();
	
	$scope.cartClicked = function() {
		//createOrder();
		if(dataShareService.getProdList() == null 
				|| dataShareService.getProdList().length == 0) {
			$window.alert("Please select products");			
		} else {
			$location.path("/cart1");
		}
	};
	
	$scope.goToProdPage = function() {
		$location.path("/products");
	};
	
	$scope.goToOrderPage = function() {
		var customerid = dataShareService.getCustomerId();
		$location.path("/orderStatus/" + customerid);
	};
	
	$scope.isDisplayProdPage = function() {
		var currentLocation = $location.url();
		if(currentLocation == "/products"){
			return false;
		} else {
			return true;
		}
	}
	
	$scope.isDisplayProcessOrder = function() {
		var currentLocation = $location.url();
		console.log("CURRENT URL IS " + currentLocation);
		if(currentLocation == "/orderStatus/" + dataShareService.getCustomerId()){
			return true;
		} else {
			return false;
		}
	};
	
	var onUpdateOrderSuccess = function(data) {
		var customerid = dataShareService.getCustomerId();
		$location.path("/orderStatus/process/" + customerid);
		
	};
	
	var onUpdateOrderFailure = function(data) {
		$window.alert("Order Failure");
	};
	
	$scope.processOrder = function() {
		vraservice.updateOrder().then(onUpdateOrderSuccess, onUpdateOrderFailure);
	};
	
});

app.controller('prodCartController', function($scope, vraservice, dataShareService, $window, $location) {
	
	$scope.continueBtnClk = function() {
		console.log(angular.toJson($scope.prodList));
		
		for(var i = ($scope.prodList.length - 1); i >= 0; i--) {
			if(!$scope.prodList[i].isProd) {
				dataShareService.getProdList().splice(i, 1)
			}
		}
		
		console.log(angular.toJson($scope.prodList));
		
		if(dataShareService.getProdList() == null 
				|| dataShareService.getProdList().length == 0) {
			$window.alert("Please select products");
			$location.path("/products/");
		} else {
			$location.path("/cart2/");
		}
	}
	
	$scope.prodList = dataShareService.getProdList();
});

app.controller('cartController', function($scope, vraservice, dataShareService, $window, $location) {
	
	var customerid = dataShareService.getCustomerId();
	
	var onGetAddressComplete = function(data) {
		console.log(data);
		$scope.custAddress = data.addresslist[0];
		console.log(angular.toJson($scope.custAddress));
	};
	
	var onGetAddressError = function(response) {
		
	};
	
	
	vraservice.getCustomerAddress(customerid).then(onGetAddressComplete, onGetAddressError);
	
	
	$scope.backBtn = function() {
		$location.path("/cart1/");
	};
	
	var onCreateOrderSuccess = function(data) {
		if(data.status == 'Success') {
			dataShareService.resetProdList();
			$scope.cartList = dataShareService.getProdList();
			
			var customerid = dataShareService.getCustomerId();
			$location.path("/orderStatus/" + customerid);
		} else {
			onCreateOrderError();
		}
	};
	
	var onCreateOrderError = function() {
		$window.alert("Error in creating the order");
	};
	
	var createOrder = function(){
		var reqObj = createOrderObject();
		console.log(reqObj);
		vraservice.createOrder(reqObj).then(onCreateOrderSuccess, onCreateOrderError);
	};
	
	var createOrderObject = function() {
		console.log("Inside createOrderObject");
		var reqObj = {
					"customerId":dataShareService.getCustomerId(),
					"statusId":0,
					"orderLineBVOList":dataShareService.getProdList()
			} ;
		
		return angular.toJson(reqObj);
	};
	
	$scope.continueBtn = function() {
		createOrder();
	};
	
});

app.controller('loginController', function($scope, vraservice, $window, $location, dataShareService) {
	
	$scope.user = {
			username: "vmware",
			password: "vmware",
	    };
	
	$scope.invalidUser = null;
	
	var onValidationComplete = function(data) {
    		console.log(data);
    		if(data.customerId != null) {
    			$scope.invalidUser = null;
    			//if we got a valid user with correct credentials, load the next page
    			//$location.path("/orderStatus/" + data.customerId);
    			dataShareService.setCustomerId(data.customerId);
    			
    			dataShareService.setCustomerInfo(data);
    			$location.path("/products");
    		} else {
    			$scope.invalidUser = 'Invalid Credentials';
    		}
	};
	
	var onError = function(reason) {
		$scope.invalidUser = 'Unknown error while user validation';
	};
	
	$scope.validateLogin = function() {
		
		var host = $location.host();
		console.log(host);
		
		var h = location.host;
		console.log(h);
		
		console.log($location.absUrl());
		
		vraservice.uservalidation(
				$scope.user.username, 
				$scope.user.password).then(onValidationComplete, onError);
	};
	
});