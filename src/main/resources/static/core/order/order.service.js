angular.
  module('core.order').
  factory('Order', ['$resource',
    function($resource) {
      return $resource('orders', {}, {
        create: {
          method: 'POST'
        },
        addItem: {
          url: 'orders/:orderId/items/:itemName',
          params: {
            orderId: '@orderId',
            itemName: '@itemName'
          },
          method: 'POST'
        },
        removeItem: {
          url: 'orders/:orderId/items/:itemName',
          params: {
            orderId: '@orderId',
            itemName: '@itemName'
          },
          method: 'DELETE'
        },
        recordTender: {
          url: 'orders/:orderId/tender',
          params: {
            orderId: '@orderId'
          },
          method: 'POST'
        }
      });
    }
]);
