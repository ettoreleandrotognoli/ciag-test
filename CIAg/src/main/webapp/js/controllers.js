var usersControllers = angular.module('usersControllers', []);

usersControllers.controller('userList', ['$scope', 'User',
    function ($scope, User) {
        $scope.users = User.query();

        $scope.delete = function (user) {
            user.$remove({id: user.id}, function (success) {
                $scope.users.splice($scope.users.indexOf(user), 1);
            });
        };
    }
]);

usersControllers.controller('userAdd', ['$scope', '$location', 'User',
    function ($scope, $location, User) {

        $scope.user = {};

        $scope.save = function () {
            User.save($scope.user, function (success) {
                $location.path('/users/');
            });
        };
    }
]);

usersControllers.controller('userDetail', ['$scope', '$routeParams', '$location', 'User',
    function ($scope, $routeParams, $location, User) {
        $scope.user = User.get({id: $routeParams.userId});

        $scope.save = function () {
            $scope.user.$update({id: $scope.user.id}, function (success) {
                $location.path('/users/');
            });

        };

        $scope.delete = function () {
            $scope.user.$remove({id: $scope.user.id}, function (success) {
                $location.path('/users/');
            });
        };
    }
]);
