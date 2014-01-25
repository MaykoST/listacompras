'use strict';

/* Controllers */

angular.module('myApp.controllers', ['ui.bootstrap']).
        controller('ListaController', ['$scope', '$modal', '$log', 'ListaCompras', function($scope, $modal, $log, ListaCompras) {
                /*$scope.open = function(item) {
                 var modalInstance = $modal.open({
                 templateUrl: 'partials/criaeditaitem.html',
                 controller: 'ModalInstanceCtrl',
                 resolve: {
                 item: function() {
                 return item;
                 }
                 }
                 });
                 
                 modalInstance.result.then(function() {
                 $scope.load();
                 }, function() {
                 $log.info('Modal dismissed at: ' + new Date());
                 });
                 };*/
               

                /*
                 $scope.select = function(item) {
                 $scope.cur_item = item;
                 };*/

                $scope.limparComprado = function() {
                    var remover = $scope.items.filter(function(item) {                        
                        return item.comprado;                        
                    });
                    
                    $scope.items = $scope.items.filter(function(item) {
                        return !item.comprado;
                    })

                    for (var i = 0; i < remover.length; i++) {
                        ListaCompras.delete({id: remover[i].id}, function(data) {                            
                        }, function(error) {
                            //Recarrega caso acontecer um erro
                            $scope.carregar();
                        });
                    }                    
                }

                $scope.adicionarItem = function() {
                    ListaCompras.save({descItem: $scope.itemAtual.descItem, comprado:false}, function(data) {
                        $scope.items.push(data);
                        $scope.itemAtual.descItem = '';
                        //$scope.carregar();
                    }, function(error) { 
                        $scope.carregar();
                    });
                }
                
                $scope.atualizarItem = function(item) {
                    item.comprado = !item.comprado;
                    
                    ListaCompras.update({id:item.id}, item, function(data, status) {                        
                    },
                    function(error) {
                        $scope.carregar();
                    });
                }

                $scope.carregar = function() {
                    $scope.items = ListaCompras.query();
                }

                $scope.foiComprado = function(comprado) {
                    return comprado ? 'comprado' : 'nao-comprado';
                }

                $scope.carregar();
            }])
        .controller('ModalInstanceCtrl', ['$scope', '$modalInstance', 'ListaCompras', 'item',
            function($scope, $modalInstance, ListaCompras, item) {

                if (item != null) {
                    $scope.cur_item = item;
                } else {
                    $scope.cur_item = {};
                    $scope.cur_item.id = null;
                    $scope.cur_item.descricao = "";
                    $scope.cur_item.data = '2014-01-14T22:34:00';
                }

                $scope.inserir = function() {

                };

                $scope.ok = function() {
                    if ($scope.cur_item.id != null) {
                        ListaCompras.update($scope.cur_item, function() {
                            $modalInstance.close(/*$scope.selected.item*/);
                        });
                    } else {
                        ListaCompras.save($scope.cur_item, function() {
                            $modalInstance.close(/*$scope.selected.item*/);
                        });
                    }
                };

                $scope.cancel = function() {
                    $modalInstance.dismiss('cancel');
                };
            }]);

