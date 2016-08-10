angular.
  module('orderDetail').
  component('orderDetail', {
    templateUrl: 'order-detail/order-detail.template.html',
    controller: ['Order', '$rootScope',
      function OrderDetailController(Order, $rootScope) {
        var orderDetail = this;

        orderDetail.createOrder = function() {
          orderDetail.order = Order.create();
          orderDetail.amtTendered = 0.00;
          orderDetail.currentItem = null;
        }
        orderDetail.createOrder();

        orderDetail.orderName = function() {
          if (!orderDetail.order || !orderDetail.order.orderNumber) {
            return 'New Order';
          } else {
            return 'Order #' + orderDetail.order.orderNumber;
          }
        };

        $rootScope.$on('itemAdded', function(event, item) {
          if (!orderDetail.order.tenderRecord) {
            orderDetail.order = Order.addItem({
              orderId: orderDetail.order.id,
              itemName: item.name
            });
          }
        });

        orderDetail.setCurrentItem = function(lineItem) {
          if (!orderDetail.order.tenderRecord) {
            orderDetail.currentItem = lineItem;
          }
        }

        orderDetail.isCurrentItem = function(lineItem) {
          return orderDetail.currentItem == lineItem;
        }

        orderDetail.removeCurrentItem = function() {
          orderDetail.order = Order.removeItem({
            orderId: orderDetail.order.id,
            itemName: orderDetail.currentItem.name
          });
          orderDetail.currentItem = null;
        }

        orderDetail.hasItems = function() {
          return !orderDetail.order.tenderRecord && orderDetail.order
              && orderDetail.order.lineItems && orderDetail.order.lineItems.length;
        }

        orderDetail.recordTender = function() {
          orderDetail.order = Order.recordTender({
            orderId: orderDetail.order.id,
            amountTendered: orderDetail.amtTendered
          });
          orderDetail.currentItem = null;
        }
      }
    ]
});
