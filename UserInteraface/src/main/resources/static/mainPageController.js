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
    $scope.addEmployee = function(employee){
        console.log(employee);
        /*$http({
            method: 'POST',
            url: "http://localhost:8079/services/employees/addEmployee",
            data: $scope.employee,
        }).success(function () {});*/
        $http.post('http://localhost:8079/services/employees/addEmployee', employee)
            .success(function(result){
                console.log('emp successfully added');
                $scope.employees.push(result);
        });
    };

});