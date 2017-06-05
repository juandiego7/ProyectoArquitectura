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
                                       if(data.data == "S"){   
                                           alert($location.absUrl());
                                            $cookies.username = $scope.username;
                                            $location.url("/index");
                                       }else{
                                           alert("no paso");
                                       }
				}
                                
			},
			function failure(data){
				alert("falla "+data.data);
			}
            );
	};
});


appCliente.config(['$routeProvider',function($routeProvider){
	
	$routeProvider.when('/',{
		templateUrl: 'login.html',
		controller:'Login'
	})
                .when('/index',{
		templateUrl: 'templates/index.html'   
		
                
	});
}]);


