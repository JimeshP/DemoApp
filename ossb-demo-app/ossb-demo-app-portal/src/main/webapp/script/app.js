(function() {
	
	var app = angular.module('myApp', ["ngRoute"]);
	
	app.config(function($routeProvider) {
		$routeProvider
			.when("/vrademoapp", {
				templateUrl: "logindiv.html",
				controller: "loginController"
			})
			.when("/orderStatus/:customerid", {
				templateUrl:"orderstatustable.html",
				controller:"customersCtrl"
			})
			.when("/orderStatus/process/:customerid", {
				templateUrl:"orderstatustable.html",
				controller:"customersCtrl"
			})
			.when("/products", {
				templateUrl:"products.html",
				controller:"prodController"
			})
			.when("/registration", {
				templateUrl:"registration.html",
				controller:"regController"
			})
			.when("/cart1", {
				templateUrl:"prodcart.html",
				controller:"prodCartController"
			})
			.when("/cart2", {
				templateUrl:"paymentCart.html",
				controller:"cartController"
			})
			.otherwise({redirectTo:"/vrademoapp"});
			
			
	});
	
}());