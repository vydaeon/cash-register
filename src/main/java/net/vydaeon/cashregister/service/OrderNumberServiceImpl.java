package net.vydaeon.cashregister.service;

import net.vydaeon.cashregister.dao.OrderNumberRepository;
import net.vydaeon.cashregister.domain.OrderNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

/**
 * Default implementation of {@link OrderNumberService}.
 *
 * @author Brad Bottjen
 */
@Service
class OrderNumberServiceImpl implements OrderNumberService {

    static final Integer ORDER_NUMBER_ID = 1;
    static final int MAX_ORDER_NUMBER = 100;

    private final OrderNumberRepository repository;

    @Autowired
    OrderNumberServiceImpl(OrderNumberRepository repository) {
        this.repository = repository;
    }

    @Override
    public int getNextOrderNumber() {
        while (true) {
            try {
                OrderNumber orderNumber = repository.findOne(ORDER_NUMBER_ID);
                if (orderNumber == null) {
                    orderNumber = new OrderNumber();
                    orderNumber.setId(ORDER_NUMBER_ID);
                }

                int nextOrderNumber = orderNumber.getOrderNumber() % MAX_ORDER_NUMBER + 1;
                orderNumber.setOrderNumber(nextOrderNumber);
                repository.save(orderNumber);
                return nextOrderNumber;
            } catch (OptimisticLockingFailureException olfe) {
                continue;
            }
        }
    }
}
