'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
var listacomprasservice = angular.module('myApp.services', ['ngResource']);

listacomprasservice.factory('ListaCompras', function($resource) {
    return $resource('http://192.168.0.150:8084/listacompras/rest/listacomprasmaster', {}, {        
    'get':    {method:'GET'},
    'save':   {method:'POST'},
    'query':  {method:'GET', isArray:true},
    'remove': {method:'DELETE'},
    'delete': {method:'DELETE'}
    });
});
