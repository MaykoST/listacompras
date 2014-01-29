'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
var listacomprasservice = angular.module('myApp.services', ['ngResource']);

listacomprasservice.
        factory('ListaCompras', ['$resource', function($resource) {
                //var URL = 'http://192.168.0.150:8080/listacompras/rest/lista/:id';
                var URL = 'http://listacompras-mercado.rhcloud.com/listacompras/rest/lista/:id';
                return $resource(URL, null, {
                    'update': {method: 'PUT'}
                });
            }])
        .factory('focus', ['$rootScope', '$timeout', function($rootScope, $timeout) {
            return function(name) {
                $timeout(function() {
                    $rootScope.$broadcast('focusOn', name);
                });
            }
        }]);
