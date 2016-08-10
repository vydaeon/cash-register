angular.
  module('core.items').
  factory('Items', ['$resource',
    function($resource) {
      return $resource('items', {}, {
        findAll: {
          method: 'GET',
          isArray: true
        }
      });
    }
]);
