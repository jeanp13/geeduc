angular.module('ApplicationModule')

	.factory('Role',['RestfulFactory', function(RestfulFactory){
		return RestfulFactory.service('role');
	}])
;