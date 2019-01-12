var mainPage = angular.module('mainPage', []);
mainPage.controller('Hello', function($scope,$http){
    $http.get('http://localhost:8079/greeting').
    success(function(data) {
        $scope.greeting = data;
    });
})
mainPage.controller('EmployeeList', function($scope,$http){
    $http.get('http://localhost:8079/services/employees/getEmployees').
        success(function (data) {
        console.log(data);
            $scope.employees = data;

    });
    $scope.addEmployee = function(firstName, lastName, secondName){
        console.log(firstName, lastName, secondName);
        $http.post('http://localhost:8079/services/employees/addEmployee', firstName)
            .success(function(result){
                console.log('Book successfully added');
        });
    };

});