angular.module('ApplicationModule')
	
	.config(function($routeProvider, $httpProvider) {
		
		$routeProvider
			.when('/room', {
				templateUrl : 'templates/room/room-list-tpl.html',
				controller : 'RoomListCtrl'
			})
			.when('/room/save', {
				templateUrl : 'templates/room/room-form-tpl.html',
				controller : 'RoomSaveCtrl'
			})
			.when('/room/update/:id', {
				templateUrl : 'templates/room/room-form-tpl.html',
				controller : 'RoomSaveCtrl'
			})
			.otherwise('/');
	
	})
	
	.controller('RoomListCtrl', function($scope, $location, $filter,  localStorageService, Room, Hospital, User ,NgTableParams, LoginService, AlertService){

		// ng-clik 'editUser'
		$scope.updateRoom = function(id){
			$location.path('/room/update/'+id);
		};

		// ng-clik 'deleteUser'
		$scope.deleteRoom = function(room, index){
			if( confirm("Deseja realmente deletar o quarto: " + room.number) == true ){
				Room.one(user.userId).remove().then(function(response){
					$scope.rooms.splice(index, 1);
					$scope.tableParams.reload();
				});
			}
		};

		Hospital.getList().then(function(hospitalResponse) {
			$scope.hospitals = hospitalResponse;

			var user = LoginService.getUser();
			User.one().one('/username/', user.username).get().then(function(userResponse){
				$scope.userHospital = userResponse.hospital;

				// TABLE --------- //
				$scope.rooms = $scope.userHospital.rooms;
				$scope.tableParams = new NgTableParams(
					{
						page: 1,
						count: 10
					},
					{
						total: $scope.rooms.length,
						getData: function ($defer, params) {

							var orderedData = params.sorting() ?
								$filter('orderBy')($scope.rooms, params.orderBy()) : $scope.rooms;

							orderedData = params.filter() ?
								$filter('filter')(orderedData, params.filter()) : orderedData;

							orderedData = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());

							//params.total(orderedData.length); // set total for recalc pagination
							$scope.tableParams.total($scope.rooms.length);

							$defer.resolve(orderedData);
						}
					}
				);
				// ---------- TABLE //

			});

		});

		$scope.changeHospital = function(hospital){
			console.log(hospital);
			$scope.rooms = hospital.rooms;
			$scope.tableParams.reload();
		};
			
	})
	
	.controller('RoomSaveCtrl' ,function($scope, $location, $routeParams, $filter, Room, Role, Hospital, AlertService){

		var roomId = ($routeParams.id) ? parseInt($routeParams.id) : null;
		$scope.title = (roomId != null) ? 'Editar Quarto' : 'Alterar Usuário';

	})
	
	.factory('Room',['RestfulFactory', function(RestfulFactory){
		return RestfulFactory.service('room');
	}])
;