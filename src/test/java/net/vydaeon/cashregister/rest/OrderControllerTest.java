package net.vydaeon.cashregister.rest;

import net.vydaeon.cashregister.domain.Order;
import net.vydaeon.cashregister.service.OrderService;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.OK;

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

    @Test
    public void removeItem() {
        String orderId = "order ID";
        String itemName = "item name";
        when(orderService.removeItem(orderId, itemName)).thenReturn(savedOrder);

        Order order = orderController.removeItem(orderId, itemName);
        assertThat(order, is(savedOrder));
    }

    @Test
    public void recordTender() {
        String orderId = "order ID";
        BigDecimal amountTendered = new BigDecimal("11.23");
        when(orderService.recordTender(orderId, amountTendered)).thenReturn(savedOrder);

        ResponseEntity<?> responseEntity = orderController.recordTender(orderId, amountTendered);
        assertThat(responseEntity, is(notNullValue()));
        assertThat(responseEntity.getStatusCode(), is(OK));
        assertThat(responseEntity.getBody(), is(savedOrder));
    }

    @Test
    public void recordTender_when_IllegalStateException() {
        String errorMessage = "TEST TEST TEST";
        when(orderService.recordTender(any(), any()))
                .thenThrow(new IllegalStateException(errorMessage));

        ResponseEntity<?> responseEntity = orderController.recordTender(
                "order ID", new BigDecimal("11.23"));
        assertThat(responseEntity, is(notNullValue()));
        assertThat(responseEntity.getStatusCode(), is(CONFLICT));
        assertThat(responseEntity.getBody(), is(errorMessage));
    }
}
