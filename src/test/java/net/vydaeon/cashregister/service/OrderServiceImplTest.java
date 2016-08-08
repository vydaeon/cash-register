package net.vydaeon.cashregister.service;

import net.vydaeon.cashregister.dao.ItemRepository;
import net.vydaeon.cashregister.dao.OrderRepository;
import net.vydaeon.cashregister.dao.SalesTaxRateRepository;
import net.vydaeon.cashregister.domain.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    private static final String ORDER_ID = "order ID";
    private static final String EXISTING_ITEM_NAME = "existing item name";
    private static final BigDecimal EXISTING_ITEM_PRICE = new BigDecimal("1.23");
    private static final double TAX_RATE = 0.08;
    private static final String NEW_ITEM_NAME = "new item name";
    private static final BigDecimal NEW_ITEM_PRICE = new BigDecimal("2.34");
    private static final BigDecimal TENDER_AMOUNT = new BigDecimal("2");
    private static final int ORDER_NUMBER = 12;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private SalesTaxRateRepository salesTaxRateRepository;

    @Mock
    private OrderNumberService orderNumberService;

    @Mock
    private Order savedOrder;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Instant startInstant;

    @Before
    public void before() {
        mockTaxRate();
        mockItems();
    }

    @Test
    public void createOrder() {
        when(orderRepository.save(any(Order.class))).thenAnswer(this::verifyOrderInitialFields);
        startInstant = Instant.now();

        Order order = orderService.createOrder();
        assertThat(order, is(savedOrder));
    }

    @Test
    public void addItem_adds_new_item() {
        mockExistingOrder();
        when(orderRepository.save(any(Order.class))).thenAnswer(this::verifyOrderWithNewItemAdded);

        Order order = orderService.addItem(ORDER_ID, NEW_ITEM_NAME);
        assertThat(order, is(savedOrder));
    }

    @Test
    public void addItem_increments_quantity_for_existing_item() {
        mockExistingOrder();
        when(orderRepository.save(any(Order.class))).thenAnswer(this::verifyOrderWithExistingItemAdded);

        Order order = orderService.addItem(ORDER_ID, EXISTING_ITEM_NAME);
        assertThat(order, is(savedOrder));
    }

    @Test
    public void addItem_to_empty_order() {
        mockExistingOrderWithNoItems();
        when(orderRepository.save(any(Order.class))).thenAnswer(this::verifyEmptyOrderWithNewItemAdded);

        Order order = orderService.addItem(ORDER_ID, NEW_ITEM_NAME);
        assertThat(order, is(savedOrder));
    }

    @Test
    public void removeItem() {
        mockExistingOrderWithMultipleItems();
        when(orderRepository.save(any(Order.class))).thenAnswer(this::verifyOrderWithExistingItemRemoved);

        Order order = orderService.removeItem(ORDER_ID, EXISTING_ITEM_NAME);
        assertThat(order, is(savedOrder));
    }

    @Test
    public void recordTender() {
        mockExistingOrder();
        when(orderNumberService.getNextOrderNumber()).thenReturn(ORDER_NUMBER);
        when(orderRepository.save(any(Order.class))).thenAnswer(this::verifyOrderWithTenderRecord);
        startInstant = Instant.now();

        Order order = orderService.recordTender(ORDER_ID, TENDER_AMOUNT);
        assertThat(order, is(savedOrder));
    }

    @Test
    public void recordTender_when_TenderRecord_already_exists() {
        mockExistingOrderWithTenderRecord();
        expectedException.expect(IllegalStateException.class);
        orderService.recordTender(ORDER_ID, TENDER_AMOUNT);
    }

    private void mockExistingOrder() {
        OrderLineItem lineItem = new OrderLineItem();
        lineItem.setName(EXISTING_ITEM_NAME);
        lineItem.setQty(1);
        lineItem.setPrice(EXISTING_ITEM_PRICE);
        lineItem.setExtendedPrice(EXISTING_ITEM_PRICE);

        List<OrderLineItem> lineItems = new ArrayList<>();
        lineItems.add(lineItem);

        Order order = new Order();
        order.setLineItems(lineItems);
        order.setSubTotal(EXISTING_ITEM_PRICE);
        order.setTotalTax(new BigDecimal("0.09"));
        order.setGrandTotal(new BigDecimal("1.32"));
        when(orderRepository.findOne(ORDER_ID)).thenReturn(order);
    }

    private void mockExistingOrderWithMultipleItems() {
        OrderLineItem lineItem1 = new OrderLineItem();
        lineItem1.setName(EXISTING_ITEM_NAME);
        lineItem1.setQty(1);
        lineItem1.setPrice(EXISTING_ITEM_PRICE);
        lineItem1.setExtendedPrice(EXISTING_ITEM_PRICE);

        OrderLineItem lineItem2 = new OrderLineItem();
        lineItem2.setName(NEW_ITEM_NAME);
        lineItem2.setQty(1);
        lineItem2.setPrice(NEW_ITEM_PRICE);
        lineItem2.setExtendedPrice(NEW_ITEM_PRICE);

        List<OrderLineItem> lineItems = new ArrayList<>();
        lineItems.add(lineItem1);
        lineItems.add(lineItem2);

        Order order = new Order();
        order.setLineItems(lineItems);
        order.setSubTotal(new BigDecimal("3.57"));
        order.setTotalTax(new BigDecimal("0.28"));
        order.setGrandTotal(new BigDecimal("3.85"));
        when(orderRepository.findOne(ORDER_ID)).thenReturn(order);
    }

    private void mockExistingOrderWithNoItems() {
        Order order = new Order();
        order.setSubTotal(BigDecimal.ZERO);
        order.setTotalTax(BigDecimal.ZERO);
        order.setGrandTotal(BigDecimal.ZERO);
        when(orderRepository.findOne(ORDER_ID)).thenReturn(order);
    }

    private void mockExistingOrderWithTenderRecord() {
        Order order = new Order();
        order.setTenderRecord(new TenderRecord());
        order.setOrderNumber(ORDER_NUMBER);
        when(orderRepository.findOne(ORDER_ID)).thenReturn(order);
    }

    private void mockTaxRate() {
        SalesTaxRate taxRate = new SalesTaxRate();
        taxRate.setRate(TAX_RATE);
        when(salesTaxRateRepository.getSalesTaxRate()).thenReturn(taxRate);
    }

    private void mockItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(EXISTING_ITEM_NAME, EXISTING_ITEM_PRICE));
        items.add(new Item(NEW_ITEM_NAME, NEW_ITEM_PRICE));
        when(itemRepository.getItems()).thenReturn(items);
    }

    private Order verifyOrderInitialFields(InvocationOnMock invocation) {
        Instant endInstant = Instant.now();
        Order order = invocation.getArgumentAt(0, Order.class);
        assertThat(order, is(notNullValue()));
        assertThat(order.getId(), is(notNullValue()));
        assertThat(order.getOrderNumber(), is(nullValue()));
        assertThat(order.getTimestamp(), is(notNullValue()));
        assertThat(order.getTimestamp(), is(greaterThanOrEqualTo(startInstant)));
        assertThat(order.getTimestamp(), is(lessThanOrEqualTo(endInstant)));
        assertThat(order.getSubTotal(), is(BigDecimal.ZERO));
        assertThat(order.getTotalTax(), is(BigDecimal.ZERO));
        assertThat(order.getGrandTotal(), is(BigDecimal.ZERO));
        return savedOrder;
    }

    private Order verifyOrderWithNewItemAdded(InvocationOnMock invocation) {
        Order order = invocation.getArgumentAt(0, Order.class);
        assertThat(order, is(notNullValue()));

        List<OrderLineItem> orderLineItems = order.getLineItems();
        assertThat(orderLineItems, is(notNullValue()));
        assertThat(orderLineItems.size(), is(2));

        OrderLineItem lineItem = orderLineItems.get(1);
        assertThat(lineItem, is(notNullValue()));
        assertThat(lineItem.getName(), is(NEW_ITEM_NAME));
        assertThat(lineItem.getQty(), is(1));
        assertThat(lineItem.getPrice(), is(NEW_ITEM_PRICE));
        assertThat(lineItem.getExtendedPrice(), is(NEW_ITEM_PRICE));
        assertThat(order.getSubTotal(), is(new BigDecimal("3.57")));
        assertThat(order.getTotalTax(), is(new BigDecimal("0.28")));
        assertThat(order.getGrandTotal(), is(new BigDecimal("3.85")));
        return savedOrder;
    }

    private Order verifyOrderWithExistingItemAdded(InvocationOnMock invocation) {
        Order order = invocation.getArgumentAt(0, Order.class);
        assertThat(order, is(notNullValue()));

        List<OrderLineItem> orderLineItems = order.getLineItems();
        assertThat(orderLineItems, is(notNullValue()));
        assertThat(orderLineItems.size(), is(1));

        OrderLineItem lineItem = orderLineItems.get(0);
        assertThat(lineItem, is(notNullValue()));
        assertThat(lineItem.getName(), is(EXISTING_ITEM_NAME));
        assertThat(lineItem.getQty(), is(2));
        assertThat(lineItem.getPrice(), is(EXISTING_ITEM_PRICE));
        assertThat(lineItem.getExtendedPrice(), is(new BigDecimal("2.46")));
        assertThat(order.getSubTotal(), is(new BigDecimal("2.46")));
        assertThat(order.getTotalTax(), is(new BigDecimal("0.19")));
        assertThat(order.getGrandTotal(), is(new BigDecimal("2.65")));
        return savedOrder;
    }

    private Order verifyEmptyOrderWithNewItemAdded(InvocationOnMock invocation) {
        Order order = invocation.getArgumentAt(0, Order.class);
        assertThat(order, is(notNullValue()));

        List<OrderLineItem> orderLineItems = order.getLineItems();
        assertThat(orderLineItems, is(notNullValue()));
        assertThat(orderLineItems.size(), is(1));

        OrderLineItem lineItem = orderLineItems.get(0);
        assertThat(lineItem, is(notNullValue()));
        assertThat(lineItem.getName(), is(NEW_ITEM_NAME));
        assertThat(lineItem.getQty(), is(1));
        assertThat(lineItem.getPrice(), is(NEW_ITEM_PRICE));
        assertThat(lineItem.getExtendedPrice(), is(NEW_ITEM_PRICE));
        assertThat(order.getSubTotal(), is(NEW_ITEM_PRICE));
        assertThat(order.getTotalTax(), is(new BigDecimal("0.18")));
        assertThat(order.getGrandTotal(), is(new BigDecimal("2.52")));
        return savedOrder;
    }

    private Order verifyOrderWithExistingItemRemoved(InvocationOnMock invocation) {
        //existingOrderWithMultipleItems minus existingItem is emptyOrderWithNewItemAdded
        return verifyEmptyOrderWithNewItemAdded(invocation);
    }

    private Order verifyOrderWithTenderRecord(InvocationOnMock invocation) {
        Instant endInstant = Instant.now();
        Order order = invocation.getArgumentAt(0, Order.class);
        assertThat(order, is(notNullValue()));
        assertThat(order.getOrderNumber(), is(ORDER_NUMBER));

        TenderRecord tenderRecord = order.getTenderRecord();
        assertThat(tenderRecord, is(notNullValue()));
        assertThat(tenderRecord.getAmountTendered(), is(TENDER_AMOUNT));
        assertThat(tenderRecord.getChangeGiven(), is(new BigDecimal("0.68")));
        assertThat(tenderRecord.getTimestamp(), is(notNullValue()));
        assertThat(tenderRecord.getTimestamp(), is(greaterThanOrEqualTo(startInstant)));
        assertThat(tenderRecord.getTimestamp(), is(lessThanOrEqualTo(endInstant)));
        return savedOrder;
    }
}
