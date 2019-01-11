var mainPage = angular.module('mainPage', []);
mainPage.controller('Hello', function($scope,$http){
    $http.get('http://localhost:8080/greeting').
    success(function(data) {
        $scope.greeting = data;
    });
})
mainPage.controller('EmployeeList', function($scope,$http){
    $http.get('http://localhost:8080/getEmployees').
        success(function (data) {
            $scope.employees = data;

    })
})