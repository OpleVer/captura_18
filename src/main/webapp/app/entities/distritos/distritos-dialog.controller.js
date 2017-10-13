(function() {
    'use strict';

    angular
        .module('captura18App')
        .controller('DistritosDialogController', DistritosDialogController);

    DistritosDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Distritos'];

    function DistritosDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Distritos) {
        var vm = this;

        vm.distritos = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.distritos.id !== null) {
                Distritos.update(vm.distritos, onSaveSuccess, onSaveError);
            } else {
                Distritos.save(vm.distritos, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('captura18App:distritosUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
