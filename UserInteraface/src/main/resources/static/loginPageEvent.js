var mainPage = angular.module('loginPage', []);


mainPage.controller('SingInCtrl', function ($scope, $http) {
    $scope.singIn = function (user){
        $http.post("/services/authorisation-service/sing-in", user)
            .success(function (result) {
            });
    }

});