package net.vydaeon.cashregister.service;

/**
 * Service interface for order numbers.
 *
 * @author Brad Bottjen
 */
public interface OrderNumberService {

    /**
     * @return the next order number (1 - 100) for a newly-submitted order.
     */
    int getNextOrderNumber();
}
