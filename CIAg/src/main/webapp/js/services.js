var userServices = angular.module('userServices', ['ngResource']);

userServices.factory('User', ['$resource',
    function ($resource) {
        return $resource('api/users/:id', {id: '@id'}, {
            update: {method: 'PUT'},
            save: {method: 'POST'},
            query: {mehtod: 'GET', params: {id: ''}, isArray: true}
            //query: {method:'GET',params:{userId:''},isArray:true},
            //delete: {method:'DELETE',params:{userId:''}},
            //post : {method:'POST'},
            //put :  {method:'PUT',params:{userId:''}}
        });
    }
]);