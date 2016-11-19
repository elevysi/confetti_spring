'use strict';

var confettiApp = {};

var App = angular.module('confettiApp', ['confettiApp.filters', 'confettiApp.services', 'confettiApp.directives', 'ngRoute', 'ngResource']);

// Declare app level module which depends on filters, and services
App.config(['$routeProvider', function ($routeProvider) {
	
	$routeProvider.when('/profileSettings', {
        templateUrl: 'partials/profile/settings.html',
        controller: profileController
    });
	
    $routeProvider.when('/userDetails', {
        templateUrl: 'partials/users/details',
        controller: CarController
    });   

    $routeProvider.otherwise({redirectTo: '/cars'});
}]);
