'use strict';

/* Controllers */

angular.module('myApp.controllers', ['ui.bootstrap']).
        controller('MyCtrl1', ['$scope', 'ListaCompras', function($scope, ListaCompras) {
                $scope.inserir = function() {
                    ListaCompras.save({id: "", descricao: 'Teste Insercao', data: '2014-01-07T23:57:00'}, function() {
                        $scope.todaslistas = ListaCompras.query();
                    });
                };

                $scope.todaslistas = ListaCompras.query();
            }])
        .controller('MyCtrl2', [function() {

            }])
        .controller('ModalInstanceCtrl', ['$scope', '$modalInstance', 'items',
            function($scope, $modalInstance, items) {
                $scope.items = items;
                $scope.selected = {
                    item: $scope.items[0]
                };

                $scope.ok = function() {
                    $modalInstance.close($scope.selected.item);
                };

                $scope.cancel = function() {
                    $modalInstance.dismiss('cancel');
                };
            }])
        .controller('ModalDemoCtrl', ['$scope', '$modal', '$log',
            function($scope, $modal, $log) {
                $scope.items = ['item1', 'item2', 'item3'];

                $scope.open = function() {
                    var modalInstance = $modal.open({
                        templateUrl: 'partials/criaeditaitem.html',
                        controller: 'ModalInstanceCtrl',
                        resolve: {
                            items: function() {
                                return $scope.items;
                            }
                        }
                    });


                    var ModalDemoCtrl = modalInstance.result.then(function(selectedItem) {
                        $scope.selected = selectedItem;
                    }, function() {
                        $log.info('Modal dismissed at: ' + new Date());
                    });
                };
            }]);

