var loginPage = angular.module('loginPage', ['ngRoute','ngCookies']);

loginPage.config(function ($routeProvider){
    $routeProvider
        .when('/sing-up', {
            templateUrl: 'singUp.html'
        })
});

loginPage.controller('SingInCtrl', function ($scope, $http, $cookies) {
    $scope.singIn = function (user){
        console.log("send");
        $http.post("/services/authorisation-service/sing-in", user)
            .success(function (result) {
                console.log(result);
                $cookies.token = result.token;
                console.log("token");
                console.log($cookies.token);

            })
            .error(function (result) {
            console.log(result);

        });
    };
    $scope.singUp = function (userCreation) {
        $http.post("/services/authorisation-service/sing-up", userCreation)
            .success(function (result){
                console.log(result);
            })
            .error(function (result) {
                console.log(result);

            });

    };


});