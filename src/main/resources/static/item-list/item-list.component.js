angular.
  module('itemList').
  component('itemList', {
    templateUrl: 'item-list/item-list.template.html',
    controller: ['Items', '$rootScope',
      function ItemListController(Items, $rootScope) {
        this.items = Items.findAll();
        this.addItem = function(item) {
          $rootScope.$broadcast('itemAdded', item);
        };
      }
    ]
});
