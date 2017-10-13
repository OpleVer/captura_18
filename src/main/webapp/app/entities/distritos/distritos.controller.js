(function() {
    'use strict';

    angular
        .module('captura18App')
        .controller('DistritosController', DistritosController);

    DistritosController.$inject = ['Distritos'];

    function DistritosController(Distritos) {

        var vm = this;

        vm.distritos = [];

        loadAll();

        function loadAll() {
            Distritos.query(function(result) {
                vm.distritos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
