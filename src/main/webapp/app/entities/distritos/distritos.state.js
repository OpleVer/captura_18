(function() {
    'use strict';

    angular
        .module('captura18App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('distritos', {
            parent: 'entity',
            url: '/distritos',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Distritos'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/distritos/distritos.html',
                    controller: 'DistritosController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('distritos-detail', {
            parent: 'distritos',
            url: '/distritos/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Distritos'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/distritos/distritos-detail.html',
                    controller: 'DistritosDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Distritos', function($stateParams, Distritos) {
                    return Distritos.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'distritos',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('distritos-detail.edit', {
            parent: 'distritos-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/distritos/distritos-dialog.html',
                    controller: 'DistritosDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Distritos', function(Distritos) {
                            return Distritos.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('distritos.new', {
            parent: 'distritos',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/distritos/distritos-dialog.html',
                    controller: 'DistritosDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                num_distrito: null,
                                nom_distrito: null,
                                total_actas_distrito: null,
                                lista_nominal_distrito: null,
                                actas_procesadas: null,
                                porc_actas_procesadas: null,
                                total_votos: null,
                                porc_participacion: null,
                                actas_correctas: null,
                                actas_digitalizadas: null,
                                actas_acopiadas: null,
                                total_votantes: null,
                                total_sobrantes: null,
                                votos_sacados_urna: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('distritos', null, { reload: 'distritos' });
                }, function() {
                    $state.go('distritos');
                });
            }]
        })
        .state('distritos.edit', {
            parent: 'distritos',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/distritos/distritos-dialog.html',
                    controller: 'DistritosDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Distritos', function(Distritos) {
                            return Distritos.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('distritos', null, { reload: 'distritos' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('distritos.delete', {
            parent: 'distritos',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/distritos/distritos-delete-dialog.html',
                    controller: 'DistritosDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Distritos', function(Distritos) {
                            return Distritos.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('distritos', null, { reload: 'distritos' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
