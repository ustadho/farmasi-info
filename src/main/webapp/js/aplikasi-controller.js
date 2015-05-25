angular.module('shpl.controller', ['shpl.service'])
    .controller('ItemController', ['$scope', 'ItemService',
    function($scope, ItemService) {
        $scope.items = ItemService.query();
        $scope.rowCollection = $scope.items;
        $scope.columnCollection = [
            {label: 'Kode Barang', map: 'kode_barang'},
            {label: 'Nama Barang', map: 'nama_barang'},
            {label: 'Jenis', map: 'jenis_barang'},
            {label: 'Group', map: 'group_name'}
        ];
        $scope.globalConfig = {
            isGlobalSearchActivated: true
        };
    }])
    .controller('ItemMapping', ['$scope', '$routeParams', '$http', '$location',
    function($scope,  $routeParams, $http, $location) {
        $http.get('site/all').success(function(data) {
            $scope.sites = data;
          });
        
        $scope.tampilkanItem = function(siteId){
            $http.get('item/belum-mapping/'+siteId).success(function(data) {
                console.log('tampilkan site: '+siteId);
                $scope.items=data;
                $scope.rowCollection = $scope.items;
            });
        };
        $scope.preview = function(siteId){
            return $http.post('item/belum-mapping-report/', siteId);
        };
        $scope.siteId='';
        
        
        $scope.columnCollection = [
            {label: 'Kode Barang', map: 'kode_barang', cellClass: 'width:100px'},
            {label: 'Nama Barang', map: 'nama_barang'},
            {label: 'Konsinyasi', map: 'kons'},
            {label: 'Group', map: 'group_name'},
            {label: 'Stock', map: 'stock'},
            {label: 'UOM', map: 'uom_kecil'}
        ];
        $scope.globalConfig = {
            itemsByPage: 10
        };
        
        $scope.$watch('siteId', function (newVal, oldVal) {
            if (oldVal == newVal) return;
            //alert('here :'+$scope.siteId);
            console.log($scope.siteId);
            $scope.tampilkanItem($scope.siteId);
        }, true);
    }])
    .controller('ItemOpname', ['$scope', '$routeParams', '$http', '$location',
    function($scope,  $routeParams, $http, $location) {
        $http.get('site/all').success(function(data) {
            $scope.sites = data;
          });
        
        $scope.tampilkanItem = function(siteId){
            $http.get('item/belum-opname/'+siteId).success(function(data) {
                console.log('tampilkan site: '+siteId);
                $scope.items=data;
                $scope.rowCollection = $scope.items;
            });
        };
        $scope.siteId='';
        
        
        $scope.columnCollection = [
            {label: 'Kode Barang', map: 'kode_barang', cellClass: 'width:100px'},
            {label: 'Nama Barang', map: 'nama_barang'},
            {label: 'Saldo Site', map: 'saldo_site', formatFunction: 'currency'},
            {label: 'Jml. Reak', map: 'jumlah_real', formatFunction: 'currency'},
            {label: 'Hpp', map: 'hpp', formatFunction: 'currency'},
            {label: 'No. Opname', map: 'no_opname'},
            {label: 'Lokasi', map: 'locations'}
        ];
        $scope.globalConfig = {
            itemsByPage: 10,
            isGlobalSearchActivated: true
        };
        
        $scope.$watch('siteId', function (newVal, oldVal) {
            if (oldVal == newVal) return;
            //alert('here :'+$scope.siteId);
            console.log($scope.siteId);
            $scope.tampilkanItem($scope.siteId);
        }, true);
    }])
    .controller('ItemOpnameDouble', ['$scope', '$routeParams', '$http', '$location',
    function($scope,  $routeParams, $http, $location) {
        $http.get('site/all').success(function(data) {
            $scope.sites = data;
          });
        
        $scope.tampilkanItem = function(callback){
            $http.get('item/double-opname').success(function(data) {
                $scope.items=data;
                $scope.rowCollection = $scope.items;
            });
        };
        $scope.siteId='';
        
        
        $scope.columnCollection = [
            {label: 'SiteID', map: 'site_id', cellClass: 'width:100px'},
            {label: 'Kode Barang', map: 'kode_barang', cellClass: 'width:100px'},
            {label: 'Nama Barang', map: 'nama_barang', cellClass: 'width:100px'},
            {label: 'X-kali', map: 'jml', formatFunction: 'currency'},
            {label: 'No. Opname', map: 'no_opname'},
        ];
        $scope.globalConfig = {
            itemsByPage: 10,
            isGlobalSearchActivated: true
        };
        
        $scope.$watch('siteId', function (newVal, oldVal) {
            if (oldVal == newVal) return;
            //alert('here :'+$scope.siteId);
            console.log($scope.siteId);
            $scope.tampilkanItem($scope.siteId);
        }, true);
        
        $scope.tampilkanItem();
    }])
    .controller('POController', ['$scope', '$routeParams', '$http', 'PoService',
    function($scope,  $routeParams, $http, PoService) {
        $scope.tanggal = {};
        $scope.tanggal.from =new Date();
        $scope.tanggal.to =new Date();
        $scope.items = [];
        $scope.poList=[];
        var ymd = 'yyyy-MM-dd';
        $scope.tampilkan = function(){
//            $scope.poList = PoService.query($scope.tanggal);
//            console.log($scope.poList);
//            $scope.rowCollection = $scope.poList;
            console.log(new JsSimpleDateFormat("yyyy-MM-dd").format($scope.tanggal.from));
            $http.get('po/item-revisi/'+ 
                    new JsSimpleDateFormat("yyyy-MM-dd").format($scope.tanggal.from)+'/'+
                    new JsSimpleDateFormat("yyyy-MM-dd").format($scope.tanggal.to)).success(function(data) {
                $scope.rowCollection = data;
                console.log($scope.rowCollection);
            });
        };
        
        
        
        $scope.columnCollection = [
            {label: 'No. PO', map: 'no_po'},
            {label: 'Nama Supplier', map: 'nama_supplier'},
            {label: 'Tanggal', map: 'tanggal', formatFunction: 'date'},
            {label: 'ProductID', map: 'kode_barang'},
            {label: 'Nama Barang', map: 'nama_barang'},
            {label: 'Qty', map: 'jumlah', formatFunction: 'currency', type: 'number'},
            {label: 'Harga', map: 'harga', formatFunction: 'currency', type: 'number'},
            {label: 'Discount', map: 'discount', formatFunction: 'currency', type: 'number'},
            {label: 'PPn', map: 'ppn', formatFunction: 'currency', type: 'number'},
            
            {label: 'Qty Awal', map: 'jumlah_awal', formatFunction: 'currency', type: 'number'},
            {label: 'Harga Awal', map: 'harga_awal', formatFunction: 'currency', type: 'number'},
            {label: 'Disc Awal', map: 'discount_awal', formatFunction: 'currency', type: 'number'},
            {label: 'PPn Awal', map: 'ppn_awal', formatFunction: 'currency', type: 'number'},
            
            {label: 'Tgl. Buat', map: 'tgl_buat_po'},
            {label: 'Tgl. Revisi', map: 'tgl_upd_po'},
        ];

        $scope.globalConfig = {
            isGlobalSearchActivated: true,
            isPaginationEnabled: true,
            itemsByPage: 10,
            maxSize: 8
        };
    }])
        ;