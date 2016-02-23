angular.module('ApplicationModule')
	
	.config(function($routeProvider, $httpProvider) {
		
		$routeProvider
			.when('/user', {
				templateUrl : 'templates/user/user-list-tpl.html',
				controller : 'UserListCtrl'
			})
			.when('/user/save', {
				templateUrl : 'templates/user/user-form-tpl.html',
				controller : 'UserSaveCtrl'
			})
			.when('/user/update/:id', {
				templateUrl : 'templates/user/user-form-tpl.html',
				controller : 'UserSaveCtrl'
			})
			.otherwise('/');
	
	})
	
	.controller('UserListCtrl', function($scope, $location, $filter,  localStorageService, User, NgTableParams, AlertService){

		// ng-clik 'editUser'
		$scope.updateUser = function(userId){
			$location.path('/user/update/'+userId);
		};

		// ng-clik 'deleteUser'
		$scope.deleteUser = function(user, index){
			if( confirm("Deseja realmente deletar o usuário: " + user.username) == true ){

				User.one(user.userId).remove().then(function(response){
					$scope.users.splice(index, 1);
					$scope.tableParams.reload();
				});

				console.log($scope.users[index]);

			}
		};


		User.getList().then(function(response){
			$scope.users = response;

			$scope.tableParams = new NgTableParams(
				{
					page: 1,
					count: 10
				},
				{
					total: $scope.users.length,
					getData: function ($defer, params){

						var orderedData = params.sorting() ?
							$filter('orderBy')($scope.users, params.orderBy()) : $scope.users;

						orderedData = params.filter() ?
							$filter('filter')(orderedData, params.filter()) : orderedData;

						orderedData = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());

						//params.total(orderedData.length); // set total for recalc pagination
						$scope.tableParams.total($scope.users.length);

						$defer.resolve(orderedData);
					}
				}
			);
		});
			
	})
	
	.controller('UserSaveCtrl' ,function($scope, $location, $routeParams, $filter, User, Role, Hospital, AlertService){

		var userId = ($routeParams.id) ? parseInt($routeParams.id) : null;
		$scope.title = (userId != null) ? 'Editar Usuário' : 'Adicionar Usuário';


		Hospital.getList().then(function(response){
			$scope.hospitals = response;

			Role.getList().then(function(response){
				$scope.roles = response;


				if(userId != null){
					User.one(userId).get().then(function(response){

						$scope.user = response;
						$scope.user.phone = parseInt($scope.user.phone);

						var userRole = [];
						angular.forEach($scope.roles, function(roleObject, roleKey){
							angular.forEach($scope.user.roles, function(userRoleObject, userRoleKey){
								if(roleObject.roleId === userRoleObject.roleId){
									userRole.push($scope.roles[roleKey]);
								}
							});
						});
						$scope.user.roles = userRole;

					});
				}

			});

		});

		$scope.cancel = function(){
			$location.path('/user');
		};

		$scope.update = function(isValid){

			if(isValid){
				$scope.user.put().then(function(response){
					console.log(response);
				});
			}
		};


		$scope.save = function(isValid){
			if(isValid){
				if($scope.user.password === $scope.passwordConfirmation){
					User.post($scope.user).then(function(response){
						console.log(response);
					});
				} else {
					AlertService.error("As senhas informadas não são iguais!");
				}
			}
		};

	})
	
	.factory('User',['RestfulFactory', function(RestfulFactory){
		return RestfulFactory.service('user');
	}])
;