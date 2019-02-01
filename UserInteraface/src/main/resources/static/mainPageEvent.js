var mainPage = angular.module('mainPage', ['ngRoute', 'ngCookies']);


/*loginPage.config(function ($routeProvider){
   $routeProvider
       .when('/login', {
           templateUrl: 'loginPage.html'
       })
});*/

mainPage.controller('EmployeeListCtrl', function($scope,$http,$cookies){
    var token = $cookies.token;
    console.log(token);
    $http({
        method: 'GET',
        url: "http://localhost:8079/services/employee-service/employees",
        headers : {
            'Authorization': token
        }
    })
        .success(function (data) {
            console.log(data);
            $scope.employees = data;
        });

    $scope.addEmployee = function(employee){
        console.log(employee);
        $http({
            method: 'POST',
            url: "http://localhost:8079/services/employee-service/add-employee",
            data: employee,
            /*headers : {
                'token' : token
            }*/

        }).success(function (result) {
            console.log('emp successfully added');
            $scope.employees.push(result);
        });
        /*$http.post('/services/employee-service/add-employee', employee, config)
            .success(function(result){
                console.log('emp successfully added');
                $scope.employees.push(result);
            })
            .error(function (result) {
                console.log(result)
            });*/
    };
    $scope.updateEmployee = function(){
        $http.post('http://localhost:8079/services/employee-service/update-employee', $scope.employees[0])
            .success(function(result){
                console.log('emp successfully update');
                $scope.employees[0]=result;
            });
    };

});

mainPage.controller('GroupListCtrl', function($scope, $http){
    var token;
    var config = {headers:{
            'token' : token
        }
    }
    $http.get('http://localhost:8079/services/rule-service/rules')
        .success(function(data){
            $scope.rules = data;
        });
    $http.get('http://localhost:8079/services/rule-service/groups')
        .success(function(data){
            $scope.groups = data;
        });

    $scope.createGroup = function(newGroup){
        $http.post('services/rule-service/create-group',newGroup,config)
            .success(function (result) {
                console.log('group successfully added');
                $scope.groups.push(result);
            });

    }
    $scope.addEmpToGroup = function(){
        console.log('addEmpToGroup');
    }

})