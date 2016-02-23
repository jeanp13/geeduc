angular.module('ApplicationModule')

	.factory('Hospital',['RestfulFactory', function(RestfulFactory){
		return RestfulFactory.service('hospital');
	}])
;