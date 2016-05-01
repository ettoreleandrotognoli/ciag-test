var ciagApp = angular.module('ciagApp', [
    'ngRoute',
    'usersControllers',
    'userServices',
]);


ciagApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
        .when('/users', {
            templateUrl: 'user/list.html',
            controller: 'userList'
        })
        .when('/users/add',{
            templateUrl : 'user/add.html',
            controller: 'userAdd'
        })
        .when('/users/:userId',{
            templateUrl : 'user/detail.html',
            controller: 'userDetail'
        })
        .otherwise({
            redirectTo : '/users'
        });
    }
]);
