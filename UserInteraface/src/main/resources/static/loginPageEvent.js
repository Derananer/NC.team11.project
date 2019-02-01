var loginPage = angular.module('loginPage', ['ngRoute','ngCookies']);

loginPage.config(function ($routeProvider){
    $routeProvider
        .when('/sing-up', {
            templateUrl: 'singUp.html'
        })
});

loginPage.controller('SingInCtrl', function ($scope, $http, $cookies) {
    var token;
    $scope.singIn = function (user){
        console.log("send");
        //$http.post("http://localhost:8079/services/authorisation-service/login", user)
        $http({
            method : 'POST',
            url: "http://localhost:8079/services/authorisation-service/login",
            //url : "http://localhost:8083/login",
            data : user,
            headers : {
                "token" : "" ,
                "Content-type" : "application/json"
            }
        })
            .then(function (response) {
                console.log(response);
                console.log(response.headers());
                $cookies.token = response.headers()['token'];
                console.log("token" + $cookies.token);
                $http.get("/");
        });
    };
    $scope.singUp = function (userCreation) {
        $http.post("http://localhost:8079/services/authorisation-service/sing-up", userCreation)
            .success(function (result){
                console.log(result);
            })
            .error(function (result) {
                console.log(result);

            });

    };


});