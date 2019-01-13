var mainPage = angular.module('mainPage', []);
mainPage.controller('Hello', function($scope,$http){
    $http.get('/greeting').
    success(function(data) {
        $scope.greeting = data;
    });
})
mainPage.controller('EmployeeListCtrl', function($scope,$http){
    $http.get('/services/employee-service/employees').
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
        $http.post('/services/employee-service/add-employee', employee)
            .success(function(result){
                console.log('emp successfully added');
                $scope.employees.push(result);
        });
    };
    $scope.updateEmployee = function(){
        $http.post('/services/employee-service/update-employee', $scope.employees[0])
            .success(function(result){
                console.log('emp successfully update');
                $scope.employees[0]=result;
            });
    };

});

mainPage.controller('GroupListCtrl', function($scope, $http){
    $http.get('/services/rule-service/rules')
        .success(function(data){
            $scope.rules = data;
        });

})