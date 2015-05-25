angular.module('shpl', ['ui', 'smartTable.table', 'shpl.controller'])
    .config(['$routeProvider', function($routeProvider){
        $routeProvider
            .when('/', {templateUrl: 'pages/home.html'})
            .when('/401', {templateUrl: 'pages/404.html', controller: 'LoginRedirectorController'})
            .when('/item/belum-mapping', {templateUrl: 'pages/item/belum-mapping.html', controller: 'ItemMapping'})
            .when('/item/belum-opname', {templateUrl: 'pages/item/belum-opname.html', controller: 'ItemOpname'})
            .when('/item/double-opname', {templateUrl: 'pages/item/double-opname.html', controller: 'ItemOpnameDouble'})
            .when('/item/formularium', {templateUrl: 'pages/item/formularium.html', controller: 'ItemController'})
            .when('/po/item-revisi', {templateUrl: 'pages/item/po-item-revisi.html', controller: 'POController'})
            .otherwise({templateUrl: 'pages/404.html'});
    }])
    .config(['$httpProvider', function($httpProvider){
        var sessionTimeoutInterceptor = ['$rootScope', '$location', '$q', function($rootScope, $location, $q){
            function success(response){
                return response;
            }
            
            function error(response){
                if (response.status === 401) {
                    $location.path('/401');
                }
            }
            
            return function(promise) {
                return promise.then(success, error);
            }
        }];
        
        
        $httpProvider.responseInterceptors.push(sessionTimeoutInterceptor);
        $httpProvider.responseInterceptors.push('httpLoadingSpinner');
        var spinnerFunction = function (data, headersGetter) {
            $('#loading').show();
            return data;
        };
        $httpProvider.defaults.transformRequest.push(spinnerFunction);
    }])
    .factory('httpLoadingSpinner', function ($q, $window) {
        return function (promise) {
            return promise.then(function (response) {
                // do something on success
                $('#loading').hide();
                return response;

            }, function (response) {
                // do something on error
                $('#loading').hide();
                return $q.reject(response);
            });
        };
    })
;
