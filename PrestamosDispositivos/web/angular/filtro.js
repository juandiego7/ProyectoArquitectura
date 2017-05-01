angular.module('usuarioModule', [])
        .controller('userController', ['$scope', function ($scope) {
                $scope.noReservados =
                        [
                            {codigo: '1', dispositivo: 'Computador', descripcion: 'Asus-1001', reservar: 'button'},
                            {codigo: '2', dispositivo: 'Computador', descripcion: 'Asus-1002', reservar: 'button'},
                            {codigo: '3', dispositivo: 'Computador', descripcion: 'Asus-1003', reservar: 'button'},
                            {codigo: '4', dispositivo: 'Computador', descripcion: 'Asus-1004', reservar: 'button'},
                            {codigo: '5', dispositivo: 'Computador', descripcion: 'Asus-1005', reservar: 'button'},
                            {codigo: '6', dispositivo: 'Computador', descripcion: 'Asus-1006', reservar: 'button'},
                            {codigo: '7', dispositivo: 'Computador', descripcion: 'Asus-1007', reservar: 'button'},
                            {codigo: '8', dispositivo: 'Celular', descripcion: 'Iphon5', reservar: 'button'},
                            {codigo: '9', dispositivo: 'Celular', descripcion: 'samsung', reservar: 'button'},
                            {codigo: '10', dispositivo: 'Celular', descripcion: 'Iphon7', reservar: 'button'},
                            {codigo: '11', dispositivo: 'tablet', descripcion: 'lenovo', reservar: 'button'},
                        ];
                $scope.todos =
                        [
                            {codigo: '1', dispositivo: 'Computador', descripcion: 'Asus-1001',estado: 'disponible', reservar: 'button'},
                            {codigo: '2', dispositivo: 'Computador', descripcion: 'Asus-1002',estado: 'reservado', reservar: 'button'},
                            {codigo: '3', dispositivo: 'Computador', descripcion: 'Asus-1003',estado: 'disponible', reservar: 'button'},
                            {codigo: '4', dispositivo: 'Computador', descripcion: 'Asus-1004',estado: 'reservado', reservar: 'button'},
                            {codigo: '5', dispositivo: 'Computador', descripcion: 'Asus-1005',estado: 'disponible', reservar: 'button'},
                            {codigo: '6', dispositivo: 'Computador', descripcion: 'Asus-1006',estado: 'reservado', reservar: 'button'},
                            {codigo: '7', dispositivo: 'Computador', descripcion: 'Asus-1007',estado: 'reservado', reservar: 'button'},
                            {codigo: '8', dispositivo: 'Celular', descripcion: 'Iphon5',estado: 'disponible', reservar: 'button'},
                            {codigo: '9', dispositivo: 'Celular', descripcion: 'samsung',estado: 'disponible', reservar: 'button'},
                            {codigo: '10', dispositivo: 'Celular', descripcion: 'Iphon7',estado: 'reservado', reservar: 'button'},
                            {codigo: '11', dispositivo: 'tablet', descripcion: 'lenovo',estado: 'disponible', reservar: 'button'},
                        ];
                $scope.reservados =
                        [
                            {codigo: '1', dispositivo: 'Computador', descripcion: 'Asus-1001', reservar: 'button'},
                            {codigo: '2', dispositivo: 'Computador', descripcion: 'Asus-1002', reservar: 'button'},
                            {codigo: '3', dispositivo: 'Computador', descripcion: 'Asus-1003', reservar: 'button'},
                            {codigo: '4', dispositivo: 'Computador', descripcion: 'Asus-1004', reservar: 'button'},
                            {codigo: '5', dispositivo: 'Computador', descripcion: 'Asus-1005', reservar: 'button'},
                            {codigo: '6', dispositivo: 'Computador', descripcion: 'Asus-1006', reservar: 'button'},
                            {codigo: '7', dispositivo: 'Computador', descripcion: 'Asus-1007', reservar: 'button'},
                            {codigo: '8', dispositivo: 'Celular', descripcion: 'Iphon5', reservar: 'button'},
                            {codigo: '9', dispositivo: 'Celular', descripcion: 'samsung', reservar: 'button'},
                            {codigo: '10', dispositivo: 'Celular', descripcion: 'Iphon7', reservar: 'button'},
                            {codigo: '11', dispositivo: 'tablet', descripcion: 'lenovo', reservar: 'button'},
                        ];

                $scope.ordenarPor = function (orden) {
                    $scope.ordenSeleccionado = orden;
                }
            }]);