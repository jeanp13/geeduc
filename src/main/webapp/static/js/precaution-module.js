angular.module('ApplicationModule')

	.config(function($routeProvider, $httpProvider) {
		
		$routeProvider
			.when('/precaution', {
				templateUrl : 'templates/precaution/precaution-list-tpl.html',
				controller : 'PrecautionListCtrl'
			})
			.when('/precaution/save', {
				templateUrl : 'templates/precaution/precaution-form-tpl.html',
				controller : 'PrecautionSaveCtrl'
			})
			.when('/precaution/update/:id', {
				templateUrl : 'templates/precaution/precaution-form-tpl.html',
				controller : 'PrecautionSaveCtrl'
			})
			.otherwise('/');
	
	})
	
	.controller('PrecautionListCtrl', function($scope, $location, $filter,  localStorageService, Precaution, NgTableParams, AlertService){

		// ng-clik 'editUser'
		$scope.updatePrecaution = function(precautionId){
			$location.path('/precaution/update/'+precautionId);
		};

		// ng-clik 'deleteUser'
		$scope.deletePrecaution = function(precaution, index){
			if( confirm("Deseja realmente deletar o precaução: " + precaution.name) == true ){

				Precaution.one(precaution.precautionId).remove().then(function(response){
					$scope.precautions.splice(index, 1);
					$scope.tableParams.reload();
				});
			}
		};


		Precaution.getList().then(function(response){

			$scope.precautions = response;

			$scope.tableParams = new NgTableParams(
				{
					page: 1,
					count: 10
				},
				{
					total: $scope.precautions.length,
					getData: function ($defer, params){

						var orderedData = params.sorting() ?
							$filter('orderBy')($scope.precautions, params.orderBy()) : $scope.precautions;

						orderedData = params.filter() ?
							$filter('filter')(orderedData, params.filter()) : orderedData;

						orderedData = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());

						//params.total(orderedData.length); // set total for recalc pagination
						$scope.tableParams.total($scope.precautions.length);

						$defer.resolve(orderedData);
					}
				}
			);
		});
			
	})
	
	.controller('PrecautionSaveCtrl' ,function($scope, $location, $routeParams, $filter, Precaution,  AlertService){

		var precautionId = ($routeParams.id) ? parseInt($routeParams.id) : null;
		$scope.title = (precautionId != null) ? 'Editar Precaução' : 'Adicionar Precaução';


		if(precautionId != null){
			Precaution.one(precautionId).get().then(function(response){

				$scope.precaution = response;

			});
		}


		$scope.cancel = function(){
			$location.path('/precaution');
		};

		$scope.update = function(isValid){

			if(isValid){
				$scope.precaution.put().then(function(response){
				});
			}
		};


		$scope.save = function(isValid){
			if(isValid){
					Precaution.post($scope.precaution).then(function(response){
					});
				}
		};

	})
	
	.factory('Precaution',['RestfulFactory', function(RestfulFactory){
		return RestfulFactory.service('precaution');
	}])
;