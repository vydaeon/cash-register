package net.vydaeon.cashregister.rest;

import net.vydaeon.cashregister.domain.Order;
import net.vydaeon.cashregister.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * {@link RestController} for Order
 *
 * @author Brad Bottjen
 */
@RestController
@RequestMapping("/orders")
class OrderController {

    private final OrderService orderService;

    @Autowired
    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = POST)
    public Order createOrder() {
        return orderService.createOrder();
    }
}
