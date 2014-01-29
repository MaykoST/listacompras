'use strict';

/* Directives */


angular.module('myApp.directives', []).
        directive('appVersion', ['version', function(version) {
                return function(scope, elm, attrs) {
                    elm.text(version);
                };
            }])
        .directive('focusOn', [function() {
                return function(scope, elem, attr) {
                    scope.$on('focusOn', function(e, name) {
                        if (name === attr.focusOn) {
                            elem[0].focus();
                        }
                    });
                };
            }])
        .directive("fileread", [function() {
                return {
                    scope: {
                        fileread: "="
                    },
                    link: function(scope, element, attributes) {
                        element.bind("change", function(changeEvent) {
                            var reader = new FileReader();
                            reader.onload = function(loadEvent) {
                                scope.$apply(function() {
                                    scope.fileread = loadEvent.target.result;
                                });
                            }
                            reader.readAsDataURL(changeEvent.target.files[0]);
                        });
                    }
                }
            }]);
