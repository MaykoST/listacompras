'use strict';

/* Controllers */

angular.module('myApp.controllers', ['ui.bootstrap']).
        controller('ListaController', ['$scope', '$modal', '$log', 'ListaCompras', 'focus', function($scope, $modal, $log, ListaCompras, focus) {
                $scope.mostraFoto = function(item) {
                    var modalInstance = $modal.open({
                        templateUrl: 'partials/mostrafoto.html',
                        controller: 'ModalInstanceCtrl',
                        resolve: {
                            item: function() {
                                return item;
                            }
                        }
                    });

                    modalInstance.result.then(function() {
                    }, function() {

                    });
                };                

                $scope.adicionarItem = function() {
                    var modalInstance = $modal.open({
                        templateUrl: 'partials/item.html',
                        controller: 'InserirEditarCtrl',
                        resolve: {}
                    });

                    modalInstance.result.then(
                            function(item) {
                                $scope.items.push(item);
                                $scope.tagscomplete();
                            },
                            function(error) {
                                if (error != null) {
                                    $scope.carregar();
                                }
                            });
                }

                $scope.totalLista = function() {
                    var total = 0;
                    for (var i = 0; i < $scope.filtereditems.length; i++) {
                        total += $scope.filtereditems[i].valorItem;
                    }
                    return total;
                }
                
                $scope.tagscomplete = function (){
                    var tags = [];
                    
                    for (var i = 0; i < $scope.items.length; i++) {                                                
                        var value = $scope.items[i].tagsItem.split(',');
                        for (var j = 0; j < value.length; j++) {
                            if (tags.indexOf(value[j].toLowerCase()) == -1) {
                                tags.push(value[j].toLowerCase());
                            }                            
                        }
                    }                                        
                    $scope.tagsItems = tags;                                                                                                                        
                }

                $scope.limparComprado = function() {
                    var remover = $scope.items.filter(function(item) {
                        return item.comprado;
                    });

                    $scope.items = $scope.items.filter(function(item) {
                        return !item.comprado;
                    });
                    
                    $scope.tagscomplete();

                    for (var i = 0; i < remover.length; i++) {
                        ListaCompras.delete({id: remover[i].id}, function(data) {
                        }, function(error) {
                            //Recarrega caso acontecer um erro
                            $scope.carregar();
                        });
                    }                                        
                }

                /*
                 $scope.formkeypress = function(ev) {
                 if (ev.which == 13) {
                 $('.form-horizontal')[0].submit();
                 return false;
                 }
                 }
                 */

                /*
                 $scope.adicionarItem = function() {
                 //Carrega a image                    
                 ListaCompras.save({descItem: $scope.itemAtual.descItem,
                 comprado: false,
                 valorItem: $scope.itemAtual.valorItem || 0,
                 imgItem: $scope.itemAtual.imgItem},
                 function(data) {
                 $scope.items.push(data);
                 $scope.itemAtual.descItem = null;
                 $scope.itemAtual.valorItem = null;
                 
                 focus('focusMe');
                 //$scope.carregar();
                 }, function(error) {
                 $scope.carregar();
                 });
                 }
                 */

                $scope.atualizarItem = function(item) {
                    item.comprado = !item.comprado;

                    ListaCompras.update({id: item.id}, item, function(data, status) {
                    },
                            function(error) {
                                $scope.carregar();
                            });
                }

                $scope.carregar = function() {
                    $scope.items = ListaCompras.query(function(data) {
                        $scope.tagscomplete();
                    });                                        
                }

                $scope.foiComprado = function(comprado) {
                    return comprado ? 'comprado' : 'nao-comprado';
                }

                $scope.carregar();
            }])
        .controller('ModalInstanceCtrl', ['$scope', '$modalInstance', 'item',
            function($scope, $modalInstance, item) {
                $scope.cur_item = item;

                $scope.ok = function() {
                    $modalInstance.close();
                };
            }])
        .controller('InserirEditarCtrl', ['$scope', '$modalInstance', 'ListaCompras',
            function($scope, $modalInstance, ListaCompras) {
                $scope.itemAtual = {descItem: null, valorItem: null, imgItem: null, tagsItem: null};

                $scope.ok = function() {
                    //Grava o item
                    ListaCompras.save({descItem: $scope.itemAtual.descItem,
                        comprado: false,
                        valorItem: $scope.itemAtual.valorItem || 0,
                        imgItem: $scope.itemAtual.imgItem,
                        tagsItem: $scope.itemAtual.tagsItem},
                    function(data) {
                        $modalInstance.close(data);
                    }, function(error) {
                        //Se ocorrer um erro cancela
                        $modalInstance.dismiss(error);
                    });
                }
                $scope.cancel = function() {
                    $modalInstance.dismiss(null);
                }
            }]);

