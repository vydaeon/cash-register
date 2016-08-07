package net.vydaeon.cashregister.rest;

import net.vydaeon.cashregister.domain.Order;
import net.vydaeon.cashregister.service.OrderService;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link OrderController}.
 *
 * @author Brad Bottjen
 */
public class OrderControllerTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private OrderService orderService;

    @Mock
    private Order savedOrder;

    @InjectMocks
    private OrderController orderController;

    @Test
    public void createOrder() {
        when(orderService.createOrder()).thenReturn(savedOrder);

        Order order = orderController.createOrder();
        assertThat(order, is(savedOrder));
    }

    @Test
    public void addItem() {
        String orderId = "order ID";
        String itemName = "item name";
        when(orderService.addItem(orderId, itemName)).thenReturn(savedOrder);

        Order order = orderController.addItem(orderId, itemName);
        assertThat(order, is(savedOrder));
    }
}
