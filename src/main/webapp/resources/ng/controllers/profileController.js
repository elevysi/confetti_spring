(function() {
    var app = angular.module('confettiApp.controllers', []);
    app.controller('profileController', function($scope, $rootScope, $http, i18n, $location) {
        $scope.language = function() {
            return i18n.language;
        };
        $scope.setLanguage = function(lang) {
            i18n.setLanguage(lang);
        };
        $scope.activeWhen = function(value) {
            return value ? 'active' : '';
        };
        
        $scope.minusIconWhen = function(value) {
            return value ? 'glyphicon glyphicon-minus' : 'glyphicon glyphicon-plus';
        };

        $scope.path = function() {
            return $location.url();
        };


        $scope.logout = function() {
            $rootScope.user = null;
            $scope.username = $scope.password = null;
            $scope.$emit('event:logoutRequest');
            $location.url('/');
        };
        
        $rootScope.appUrl = "http://localhost/rpms/";
        $rootScope.reviewUrl = "https://irpreviewers.uni.lu";
        $rootScope.screenThreshold = 1600;       

    });
});