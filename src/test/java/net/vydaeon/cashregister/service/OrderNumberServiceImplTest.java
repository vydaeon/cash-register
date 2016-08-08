package net.vydaeon.cashregister.service;

import net.vydaeon.cashregister.dao.OrderNumberRepository;
import net.vydaeon.cashregister.domain.OrderNumber;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.dao.OptimisticLockingFailureException;

import static net.vydaeon.cashregister.service.OrderNumberServiceImpl.MAX_ORDER_NUMBER;
import static net.vydaeon.cashregister.service.OrderNumberServiceImpl.ORDER_NUMBER_ID;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link OrderNumberServiceImpl}.
 *
 * @author Brad Bottjen
 */
public class OrderNumberServiceImplTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private OrderNumberRepository repository;

    @InjectMocks
    private OrderNumberServiceImpl service;

    private long expectedVersionNumber = 1;
    private int expectedNextOrderNumber;

    @Test
    public void getNextOrderNumber_when_no_OrderNumber() {
        expectedVersionNumber = 0L;
        expectedNextOrderNumber = 1;
        when(repository.save(any(OrderNumber.class))).thenAnswer(this::verifySavedOrderNumber);

        int nextOrderNumber = service.getNextOrderNumber();
        verify(repository).save(any(OrderNumber.class));
        assertThat(nextOrderNumber, is(expectedNextOrderNumber));
    }

    @Test
    public void getNextOrderNumber_when_existing_OrderNumber() {
        expectedNextOrderNumber = 2;
        mockExistingOrderNumber(1);
        when(repository.save(any(OrderNumber.class))).thenAnswer(this::verifySavedOrderNumber);

        int nextOrderNumber = service.getNextOrderNumber();
        assertThat(nextOrderNumber, is(expectedNextOrderNumber));
    }

    @Test
    public void getNextOrderNumber_when_existing_OrderNumber_at_max() {
        expectedNextOrderNumber = 1;
        mockExistingOrderNumber(MAX_ORDER_NUMBER);
        when(repository.save(any(OrderNumber.class))).thenAnswer(this::verifySavedOrderNumber);

        int nextOrderNumber = service.getNextOrderNumber();
        assertThat(nextOrderNumber, is(expectedNextOrderNumber));
    }

    @Test
    public void getNextOrderNumber_when_concurrent_save() {
        when(repository.save(any(OrderNumber.class)))
                .thenThrow(new OptimisticLockingFailureException("TEST TEST TEST"))
                .thenAnswer(this::verifySavedOrderNumber);
        expectedNextOrderNumber = 3;
        when(repository.findOne(ORDER_NUMBER_ID))
                .thenReturn(createExistingOrderNumber(1))
                .thenReturn(createExistingOrderNumber(2));

        int nextOrderNumber = service.getNextOrderNumber();
        assertThat(nextOrderNumber, is(expectedNextOrderNumber));
    }

    private void mockExistingOrderNumber(int existingOrderNumber) {
        OrderNumber orderNumber = new OrderNumber();
        orderNumber.setId(ORDER_NUMBER_ID);
        orderNumber.setVersion(expectedVersionNumber);
        orderNumber.setOrderNumber(existingOrderNumber);
        when(repository.findOne(ORDER_NUMBER_ID)).thenReturn(orderNumber);
    }

    private OrderNumber createExistingOrderNumber(int existingOrderNumber) {
        OrderNumber orderNumber = new OrderNumber();
        orderNumber.setId(ORDER_NUMBER_ID);
        orderNumber.setVersion(expectedVersionNumber);
        orderNumber.setOrderNumber(existingOrderNumber);
        return orderNumber;
    }

    private OrderNumber verifySavedOrderNumber(InvocationOnMock invocation) {
        OrderNumber orderNumber = invocation.getArgumentAt(0, OrderNumber.class);
        assertThat(orderNumber, is(notNullValue()));
        assertThat(orderNumber.getId(), is(ORDER_NUMBER_ID));
        assertThat(orderNumber.getVersion(), is(expectedVersionNumber));
        assertThat(orderNumber.getOrderNumber(), is(expectedNextOrderNumber));
        return orderNumber;
    }
}
