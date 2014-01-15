'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
var listacomprasservice = angular.module('myApp.services', ['ngResource']);

listacomprasservice.factory('ListaCompras', function($resource) {
    var URL = 'http://192.168.0.150:8080/listacompras/rest/listacomprasmaster/:id';
    //var URL = 'http://listacompras-mercado.rhcloud.com/listacompras/rest/listacomprasmaster/:id';
    return $resource(URL, null, {            
    'update': {method:'PUT'}     
    });
});
