package net.vydaeon.cashregister.service;

import net.vydaeon.cashregister.dao.OrderRepository;
import net.vydaeon.cashregister.domain.Order;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;
import java.time.Instant;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link OrderServiceImpl}.
 *
 * @author Brad Bottjen
 */
public class OrderServiceImplTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private Order savedOrder;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Instant startInstant;

    @Test
    public void createOrder() {
        when(orderRepository.save(any(Order.class))).thenAnswer(this::verifyOrderInitialFields);
        startInstant = Instant.now();

        Order order = orderService.createOrder();
        assertThat(order, is(savedOrder));
    }

    private void verifyOrderInitialFields(Order order) {
        assertThat(order, is(notNullValue()));
        assertThat(order.getId(), is(notNullValue()));
        assertThat(order.getOrderNumber(), is(nullValue()));
        assertThat(order.getTimestamp(), is(notNullValue()));
        assertThat(order.getTimestamp(), is(greaterThanOrEqualTo(startInstant)));
        assertThat(order.getSubTotal(), is(BigDecimal.ZERO));
        assertThat(order.getTotalTax(), is(BigDecimal.ZERO));
        assertThat(order.getGrandTotal(), is(BigDecimal.ZERO));
    }

    private Order verifyOrderInitialFields(InvocationOnMock invocation) {
        Order order = invocation.getArgumentAt(0, Order.class);
        verifyOrderInitialFields(order);
        return savedOrder;
    }
}
