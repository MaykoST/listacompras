'use strict';

/* Controllers */

angular.module('myApp.controllers', ['ui.bootstrap']).
        controller('MyCtrl1', ['$scope', '$modal', '$log', 'ListaCompras', function($scope, $modal, $log, ListaCompras) {
                $scope.open = function(item) {
                    var modalInstance = $modal.open({
                        templateUrl: 'partials/criaeditaitem.html',
                        controller: 'ModalInstanceCtrl',
                        resolve: {
                            item: function() {
                                return item;
                            }
                        }
                    });

                    modalInstance.result.then(function(/*selectedItem*/) {
                        $scope.todaslistas = ListaCompras.query();
                    }, function() {
                        $log.info('Modal dismissed at: ' + new Date());
                    });
                };
                
                $scope.excluir = function(item) {
                    ListaCompras.delete({id:item.id}, function(){
                        $scope.todaslistas = ListaCompras.query();
                    });
                };

                $scope.todaslistas = ListaCompras.query();
            }])
        .controller('MyCtrl2', [function() {

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
                        ListaCompras.update({id: $scope.cur_item.id}, $scope.cur_item, function() {
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

