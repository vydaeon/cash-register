package net.vydaeon.cashregister.service;

import net.vydaeon.cashregister.domain.Order;

import java.math.BigDecimal;

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

    /**
     * Adds an item to an existing order.
     *
     * @param orderId  The order ID.
     * @param itemName The item name.
     * @return the {@link Order} with the item added and totals updated.
     */
    Order addItem(String orderId, String itemName);

    /**
     * Removes an item (all quantity) from an existing order.
     *
     * @param orderId  The order ID.
     * @param itemName The item name.
     * @return the {@link Order} with the item removed and totals updated.
     */
    Order removeItem(String orderId, String itemName);

    /**
     * Records a tender for an order.
     *
     * @param orderId        The order ID.
     * @param amountTendered The amount tendered.
     * @return the {@link Order}, updated with the tender record and order number.
     */
    Order recordTender(String orderId, BigDecimal amountTendered);
}
