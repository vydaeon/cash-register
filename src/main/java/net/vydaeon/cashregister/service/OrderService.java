package net.vydaeon.cashregister.service;

import net.vydaeon.cashregister.domain.Order;

/**
 * Service interface for {@link Order}s.
 *
 * @author Brad Bottjen
 */
public interface OrderService {

    /**
     * Creates a new, empty order in the system.
     *
     * @return the newly-created {@link Order}.
     */
    Order createOrder();
}
