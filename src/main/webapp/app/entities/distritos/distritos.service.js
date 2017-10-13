(function() {
    'use strict';
    angular
        .module('captura18App')
        .factory('Distritos', Distritos);

    Distritos.$inject = ['$resource'];

    function Distritos ($resource) {
        var resourceUrl =  'api/distritos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
