package net.vydaeon.cashregister.service;

import net.vydaeon.cashregister.dao.OrderRepository;
import net.vydaeon.cashregister.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

import static java.util.UUID.randomUUID;

/**
 * Default implementation of {@link OrderService}.
 *
 * @author Brad Bottjen
 */
@Service
class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder() {
        Order order = new Order();
        order.setId(randomUUID().toString());
        order.setTimestamp(Instant.now());
        order.setSubTotal(BigDecimal.ZERO);
        order.setTotalTax(BigDecimal.ZERO);
        order.setGrandTotal(BigDecimal.ZERO);
        return orderRepository.save(order);
    }
}
