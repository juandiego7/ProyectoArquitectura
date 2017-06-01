var appCliente = angular.module('usuario',['ngRoute','ngCookies']);

appCliente.service('usuarios',function($http,$cookies,$location){
	this.autenticar = function(username,password){
		return $http({
			url:'http://localhost:8080/PrestamosDispositivos/api/usuario',
			method:'GET',
			params:{
				username: username,
				password: password
			}
		});
	};
});

appCliente.controller('Login',function($scope, $location, $cookies, usuarios){
	
	$scope.username = '';
	$scope.password = '';
	
	$scope.login = function(){
            usuarios.autenticar($scope.username,$scope.password).then(
			function success(data){
				if (data.data!='') {
					alert(data.data);
					$scope.username = '';
					$scope.password = '';
					return;
				}
				$cookies.username = $scope.username;
				$location.url("/index");
			},
			function failure(data){
				alert(data.data);
			}
            );
	};
});


appCliente.config(['$routeProvider',function($routeProvider){
	
	$routeProvider.when('/',{
		templateUrl: 'login.html',
		controller:'Login'
	});
}]);


