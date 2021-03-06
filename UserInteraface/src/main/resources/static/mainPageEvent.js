var mainPage = angular.module('mainPage', ['ngRoute', 'ngCookies']);


mainPage.config(function ($routeProvider){
   $routeProvider
       .when('/home', {
           templateUrl:'mainPage.html'
       })
       .when('/login', {
           templateUrl: 'loginPage.html'
       })
       .when('/sing-up', {
           templateUrl : 'singUp.html'
       })
});

mainPage.controller('EmployeeListCtrl', function($scope,$http,$cookies,groupFactory){
    var token = $cookies.token;
    console.log(token);
    $http({
        method: 'GET',
        url: "http://localhost:8079/services/employee-service/employees",
        headers : {
            token: token,
            department : $cookies.department
        }
    })
        .success(function (data) {
            //console.log(data);
            $scope.employeeAndVacation = data;
        });

    $scope.setDaysForAll = function(days){
        $http({
            method: 'GET',
            url: "http://localhost:8079/services/employee-service/vacation/set-days-for-all-emps",
            headers : {
                token : token,
                department : $cookies.department
            },
            params : {
                days : days
            }
        }).success(function (result) {
            console.log(result);
        });
        $http({
            method: 'GET',
            url: "http://localhost:8079/services/employee-service/employees",
            headers : {
                token: token,
                department : $cookies.department
            }
        })
            .success(function (data) {
                //console.log(data);
                $scope.employeeAndVacation = data;
            });
    };
    $scope.generate = function(){
        console.log("generating");
        $http({
            method: 'GET',
            url: "http://localhost:8079/services/employee-service/vacation/generate-vacations",
            headers : {
                token : token,
                department : $cookies.department
            }
        }).success(function (result) {
            $scope.employeeAndVacation = result;
            console.log(result);
        });

    };

    $scope.updateVac = function(vacation){
        $http({
            method: 'POST',
            url: "http://localhost:8079/services/employee-service/vacation/update-vac",
            headers : {
                token : token,
                department : $cookies.department
            },
            data : {
                employeeId : employee.employee.id,
                day : vacation.day,
                month : vacation.month,
                year : vacation.year,
                numberOfDays : vacation.count
            }
        }).success(function (result) {
            vacation = result;
            console.log("setDate " + result);
        });
    };

    $scope.setDate = function(employee, vacation){
        vacation.employeeId = employee.employee.id;
        console.log(vacation);
        $http({
            method: 'POST',
            url: "http://localhost:8079/services/employee-service/vacation/set-new-date",
            headers : {
                token : token,
                department : $cookies.department
            },
            data : vacation
        }).success(function (result) {
            employee.vacations.push(result);
            console.log("setDate " + result);
        });
    };

    $scope.addEmployee = function(employee){
        console.log(employee);
        $http({
            method: 'POST',
            url: "http://localhost:8079/services/employee-service/add-employee",
            data: employee,
            headers : {
                token : token,
                department : $cookies.department
            }

        }).success(function (result) {
            console.log('emp successfully added');
            $scope.employeeAndVacation.push(result);
        });
    };
    $scope.updateEmployee = function(employee){
        $http({
            method: 'POST',
            url: "http://localhost:8079/services/employee-service/update-employee",
            data: employee,
            headers: {
                token: token,
                department: $cookies.department
            }
        })
            .success(function(result){
                console.log('emp successfully update');
                employee = result;
            });
    };
    $scope.addEmpToGroup = function(employee) {
        //console.log(employee);

        if (groupFactory.group != "undef") {
            $http({
                method: 'POST',
                url: "http://localhost:8079/services/rule-service/add-groupelem",
                headers: {
                    token: token,
                    department: $cookies.department
                },
                data: {
                    'groupId': groupFactory.group.groupId,
                    'employeeId': employee.id
                }

            })
                .success(function (result) {
                    console.log('groupElem successfully added' + result);
                    groupFactory.group.employees.push(employee);
                });
        }
    }

});

mainPage.controller('GroupListCtrl', function($scope, $http, $cookies, groupFactory){
    var token = $cookies.token;
    $http({
        method: 'GET',
        url: "http://localhost:8079/services/rule-service/rules",
        headers : {
            token : token,
            department : $cookies.department
        }
    })
        .success(function(data){
        $scope.rules = data;
    });

    $http({
        method: 'GET',
        url: "http://localhost:8079/services/rule-service/groups",
        headers : {
            token : token,
            department : $cookies.department
        }
    })
        .success(function(result) {
            console.log(result);
            $scope.groups = result;
        });
    $scope.groupInfo = function (group){
        console.log("groupId : " + group.groupId);
        $http({
            method : 'GET',
            url : "http://localhost:8079/services/employee-service/employees-by-group",
            headers : {
                token : token,
                department : $cookies.department
            },
            params : {
                groupId: group.groupId
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

    $scope.createGroup = function(rule){
        $http({
            method: 'POST',
            url: "http://localhost:8079/services/rule-service/create-group",
            headers : {
                token : token,
                department : $cookies.department
            },
            data : rule.id
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
        if(groupFactory.group == "undef") {
            groupFactory.group = group;
            group.customStyle = {'background': 'red'};
        }
        else {
            if(groupFactory.group == group) {
                groupFactory.group = "undef";
                group.customStyle = {'background': 'white'};
            }
            else {
                groupFactory.group.customStyle = {'background': 'white'};
                groupFactory.group = group;
                group.customStyle = {'background': 'red'};
            }
        }
    }


});

mainPage.factory('groupFactory', function(){
    return {
        group : "undef"
    };
});

mainPage.controller('SingInCtrl', function ($scope, $http, $cookies) {
    $scope.singIn = function (user){
        console.log("send");
        //$http.post("http://localhost:8079/services/authorisation-service/login", user)
        $http({
            method : 'POST',
            url: "http://localhost:8079/services/authorisation-service/login",
            //url : "http://localhost:8083/login",
            data : {
                username : user.username,
                password : user.password
            },
            headers : {
                "token" : "" ,
                department : "",
                "Content-type" : "application/json"
            }
        })
            .then(function (response) {
                $cookies.token = response.headers()['token'];
                $cookies.department = response.headers()['department'];
                console.log(response.headers());
                console.log("token" + $cookies.token);
                console.log("department" + $cookies.department);
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
    $scope.logOut = function (){
        $cookies.token = "";
        $cookies.department = "";

    }
});


