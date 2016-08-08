package net.vydaeon.cashregister.rest;

import net.vydaeon.cashregister.domain.Order;
import net.vydaeon.cashregister.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.CONFLICT;

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

    @PostMapping
    public Order createOrder() {
        return orderService.createOrder();
    }

    @PostMapping("/{orderId}/items/{itemName}")
    public Order addItem(@PathVariable String orderId, @PathVariable String itemName) {
        return orderService.addItem(orderId, itemName);
    }

    @DeleteMapping("/{orderId}/items/{itemName}")
    public Order removeItem(@PathVariable String orderId, @PathVariable String itemName) {
        return orderService.removeItem(orderId, itemName);
    }

    @PostMapping("/{orderId}/tender")
    public ResponseEntity<?> recordTender(@PathVariable String orderId,
                                          @RequestParam("amountTendered") BigDecimal amountTendered) {
        try {
            Order order = orderService.recordTender(orderId, amountTendered);
            return ResponseEntity.ok(order);
        } catch (IllegalStateException ise) {
            return ResponseEntity.status(CONFLICT).body(ise.getMessage());
        }
    }
}
