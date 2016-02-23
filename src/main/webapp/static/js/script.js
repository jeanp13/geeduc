'use strict';

angular.module('ApplicationModule', ['ngRoute','ngResource','restangular','ngMask','LocalStorageModule', 'ngTable', 'ngMessages', 'ui.bootstrap', 'checklist-model'])

	// GENERAL CONFIGS
	.config( function($httpProvider, localStorageServiceProvider, RestangularProvider) {

		$httpProvider.interceptors.push('httpErrorResponseInterceptor');
		localStorageServiceProvider.setStorageType('sessionStorage');

		RestangularProvider.setBaseUrl('/');
		RestangularProvider.setDefaultHeaders({
			"Accept": "*",
			"Content-Type": "application/json"
		});

	})
	
	// MAIN ROUTES
	.config(function($routeProvider, $httpProvider) {
		
		$routeProvider
			.when('/', {
				templateUrl : 'templates/home.html',
				controller : 'ApplicationCtrl'
			})
			.when('/login', {
				templateUrl : 'templates/login.html',
				controller : 'LoginCtrl'
			})
			.when('/404', {
				templateUrl : 'templates/404.html'
				//templateUrl : 'login',
			})
			.when('/400', {
				templateUrl : 'templates/404.html'
				//templateUrl : 'login',
			})
			.otherwise('/');

	})
	
	.controller('ApplicationCtrl', function($scope, $http, LoginService, localStorageService) {
		
		$scope.authenticated = function (){
			return LoginService.isAuthenticated(localStorageService);
		}

		$scope.currentUser = function () {
		    return localStorageService.get('user');
		}
		
	})
	
	.controller('LoginCtrl', function($scope, $location, $route, LoginService, localStorageService) {
		
		$scope.login = function () {
			LoginService.login($scope.credentials.username, $scope.credentials.password, localStorageService, function (response) {
				$scope.success = response.data.message;
			});
		};

		$scope.logout = function(){
			localStorageService.clearAll();
		};
		
	})
	
	.service('LoginService', function ($http, localStorageService) {

		this.login = function (username, password, localStorageService, callback) {
			$http({
				method: 'POST',
				url: '/login',
				headers: {
					Authorization: 'Basic ' + btoa(username + ':' + password)
				}	
			}).then(function (response) {
				localStorageService.set('user',response.data.user);
				callback(response.data);
			});
		};
		
		this.isAuthenticated = function (localStorageService) {
			if(localStorageService.get('user') != null){
				return true;
			} else {
				return false
			}
		};
		this.logout = function () {
		};

		this.getUser = function (localStorageService) {
			return localStorageService.get('user');
		};

		this.getUser = function () {
			return localStorageService.get('user');
		};

	})

	.controller('AlertController', function($scope, AlertService){

		$scope.alerts = AlertService.getAlerts();
		AlertService.clearAlerts();

	})

	.service('AlertService', function(){

		var _alerts = [];

		var doAlert = function (level, content) {
			if (content.constructor === Array && content.length > 0) {
				angular.forEach(content, function (item) {
					_alerts.push({ type: level, msg: item});
				});
			} else if (content.constructor !== Array && !!content) {
				_alerts.push({ type: level, msg: content});
			}
		};

		this.clearAlerts = function(){
			_alerts.length = 0;
		}

		this.getAlerts = function(){
			return _alerts;
		};

		this.success = function(content){
			doAlert('success', content);
		};

		this.warning = function(content){
			doAlert('warning', content);
		};

		this.error = function(content){
			doAlert('danger', content);
		};

	})


	.factory('RestfulFactory',['Restangular', 'AlertService', function(Restangular, AlertService){

		// Read service's response messages
		var triggerServiceMessages = function(data) {

			AlertService.success(data.successMessages);
			AlertService.warning(data.warningMessages);
			AlertService.error(data.errorMessages);

		};

		// Configure restangular error handling when request fails
		Restangular.setErrorInterceptor(function (response) {
			triggerServiceMessages(response.data);
		});


		// Configure how response will be dealt with
		return Restangular.withConfig(function (RestangularConfigurer) {

			RestangularConfigurer.addResponseInterceptor(function (data, operation, what) {

				triggerServiceMessages(data);

				if (what === 'login') {
					return data.user;
				} else {
					return data.data;
				}
			});

		});

	}])


	.factory('httpErrorResponseInterceptor', [ '$q', '$location', function($q, $location, $scope) {
			return {
				response : function(responseData) {
					return responseData;
				},
				responseError : function error(response) {
					switch (response.status) {
					case 401:
						$location.path('/login');
						break;
					case 404:
						$location.path('/404');
						break;
					case 400: // bad request
						$location.path('/400');
						break;
					case 500: // bad request
							//$location.path('/400');
							break;
					default:
						$location.path('/404');
					}

					return $q.reject(response);
				}
			};
		} ])
	
	;
