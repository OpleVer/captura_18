(function() {
    'use strict';

    angular
        .module('captura18App')
        .controller('DistritosDetailController', DistritosDetailController);

    DistritosDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Distritos'];

    function DistritosDetailController($scope, $rootScope, $stateParams, previousState, entity, Distritos) {
        var vm = this;

        vm.distritos = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('captura18App:distritosUpdate', function(event, result) {
            vm.distritos = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
