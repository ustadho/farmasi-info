angular.module('shpl.service', ['ngResource'])
    .factory('ItemService', ['$resource', '$http', function($resource, $http){
        var service = {
            item: $resource('item/formularium'),
            query : function(){return this.item.query()},
        };
        return service;
    }])
    .factory('ItemOpname', ['$resource', '$http', function($resource, $http){
        var service = {
            item: $resource('/daftar-formularium/belum-mapping'),
            query : function(){return this.item.query()},
        };
        return service;
    }])
    .factory('ItemOpnameLoss', ['$resource', '$http', function($resource, $http){
        var service = {
            item: $resource('belum-opname'),
            query : function(){return this.item.query()},
        };
        return service;
    }])
    .factory('PoService', ['$resource', '$http', function($resource, $http){
        var service = {
            po: $resource('po/item-revisi/:from/:to', {}, {
                queryPage: {method:'GET', isArray: false},
            }),
            query : function(param, callback){
                return this.po.queryPage({"from":param.from, "to":param.to}, callback) 
            },
        };
        return service;
    }])
;