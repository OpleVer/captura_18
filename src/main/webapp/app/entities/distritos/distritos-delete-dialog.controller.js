(function() {
    'use strict';

    angular
        .module('captura18App')
        .controller('DistritosDeleteController',DistritosDeleteController);

    DistritosDeleteController.$inject = ['$uibModalInstance', 'entity', 'Distritos'];

    function DistritosDeleteController($uibModalInstance, entity, Distritos) {
        var vm = this;

        vm.distritos = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Distritos.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
