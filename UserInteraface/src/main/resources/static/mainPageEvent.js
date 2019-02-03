var mainPage = angular.module('mainPage', ['ngRoute', 'ngCookies']);


/*loginPage.config(function ($routeProvider){
   $routeProvider
       .when('/login', {
           templateUrl: 'loginPage.html'
       })
});*/

mainPage.controller('EmployeeListCtrl', function($scope,$http,$cookies,groupFactory){
    var token = $cookies.token;
    console.log(token);
    $http({
        method: 'GET',
        url: "http://localhost:8079/services/employee-service/employees",
        headers : {
            token: token
        }
    })
        .success(function (data) {
            //console.log(data);
            $scope.employees = data;
        });

    $scope.addEmployee = function(employee){
        console.log(employee);
        $http({
            method: 'POST',
            url: "http://localhost:8079/services/employee-service/add-employee",
            data: employee,
            headers : {
                'token' : token
            }

        }).success(function (result) {
            console.log('emp successfully added');
            $scope.employees.push(result);
        });
    };
    $scope.updateEmployee = function(){
        $http.post('http://localhost:8079/services/employee-service/update-employee', $scope.employees[0])
            .success(function(result){
                console.log('emp successfully update');
                $scope.employees[0]=result;
            });
    };
    $scope.addEmpToGroup = function(employee) {
        //console.log(employee);
        $http({
            method: 'POST',
            url: "http://localhost:8079/services/rule-service/add-groupelem",
            headers: {
                'token': token
            },
            data: {
                'groupId': groupFactory.group.id,
                'employeeId': employee.id
            }

        })
            .success(function (result) {
                console.log('groupElem successfully added' + result);
                groupFactory.group.employees.push(employee);
            });
    }

});

mainPage.controller('GroupListCtrl', function($scope, $http, $cookies, groupFactory){
    var token = $cookies.token;
    $http({
        method: 'GET',
        url: "http://localhost:8079/services/rule-service/rules",
        headers : {
            token : token
        }
    })
        .success(function(data){
        $scope.rules = data;
    });

    $http({
        method: 'GET',
        url: "http://localhost:8079/services/rule-service/groups",
        headers : {
            token : token
        }
    })
        .success(function(result) {
            $scope.groups = result;
        });
    $scope.groupInfo = function (group){
        console.log("groupId : " + group.id);
        $http({
            method : 'GET',
            url : "http://localhost:8079/services/employee-service/employees-by-group",
            headers : {
                token : token
            },
            params : {
                groupId: group.id
            }
        })
            .success(function(result){
                console.log("result of employees-by-group: " + result);
                group.employees = result;
            });

    };
            /*var fullGroup;
            $scope.fullGroups = new Array();
            var iGr =0;
            for (var group in $scope.groups){
                console.log("groupNumber : " + group);
                console.log($scope.groups[group]);
                $scope.fullGroups.push({
                    groupInfo : $scope.groups[group],
                    employees : ""
                });
                $http({
                    method : 'GET',
                    url : "http://localhost:8079/services/employee-service/employees-by-group",
                    headers : {
                        token : token
                    },
                    params : {
                        groupId: $scope.groups[group].id
                    }
                })
                    .success(function(result){
                        console.log("result of employees-by-group: " + result);
                        $scope.fullGroups[iGr].employees = result;
                        iGr++;
                    });
                console.log("fullGroupElem: " + group);
                //console.log("fullGroup: " + fullGroup);
            }*/

    $scope.createGroup = function(newGroup){
        $http({
            method: 'POST',
            url: "http://localhost:8079/services/rule-service/create-group",
            headers : {
                token : token
            },
            data : newGroup
        })
            .success(function (result) {
                console.log('group successfully added');
                $scope.groups.push(result);
            });

    };
    /*$scope.addEmpToGroup = function(employee){
        console.log(employee);
        $http({
            method: 'POST',
            url: "http://localhost:8079/services/rule-service/add-groupelem",
            headers : {
                token: token
            },
            data : {
                groupId : groupFactory.groupId,
                employeeId : employee.id
            }

        })
            .success(function (result) {
                console.log('groupElem successfully added');
                $scope.groups.push(result);
            });
    };*/
    $scope.addEmpsToGroup = function(group){
        groupFactory.group = group;
    }


});

mainPage.factory('groupFactory', function(){
    return {
        group : ""
    };
});


