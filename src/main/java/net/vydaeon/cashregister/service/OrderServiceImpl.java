package net.vydaeon.cashregister.service;

import net.vydaeon.cashregister.dao.ItemRepository;
import net.vydaeon.cashregister.dao.OrderRepository;
import net.vydaeon.cashregister.dao.SalesTaxRateRepository;
import net.vydaeon.cashregister.domain.Item;
import net.vydaeon.cashregister.domain.Order;
import net.vydaeon.cashregister.domain.OrderLineItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;

/**
 * Default implementation of {@link OrderService}.
 *
 * @author Brad Bottjen
 */
@Service
class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final SalesTaxRateRepository salesTaxRateRepository;

    @Autowired
    OrderServiceImpl(OrderRepository orderRepository, ItemRepository itemRepository,
                     SalesTaxRateRepository salesTaxRateRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.salesTaxRateRepository = salesTaxRateRepository;
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

    @Override
    public Order addItem(String orderId, String itemName) {
        Order order = orderRepository.findOne(orderId);
        List<OrderLineItem> lineItems = getOrCreateOrderLineItems(order);
        Optional<OrderLineItem> lineItemOption = findOrderLineItem(lineItems, itemName);
        if (lineItemOption.isPresent()) {
            OrderLineItem lineItem = lineItemOption.get();
            incrementItemQuantity(order, lineItem);
        } else {
            addNewItem(order, lineItems, itemName);
        }
        return orderRepository.save(order);
    }

    @Override
    public Order removeItem(String orderId, String itemName) {
        Order order = orderRepository.findOne(orderId);
        List<OrderLineItem> lineItems = order.getLineItems();
        OrderLineItem lineItem = findOrderLineItem(lineItems, itemName).get();
        lineItems.remove(lineItem);
        increaseOrderAmounts(order, lineItem.getExtendedPrice().negate());
        return orderRepository.save(order);
    }

    private List<OrderLineItem> getOrCreateOrderLineItems(Order order) {
        List<OrderLineItem> lineItems = order.getLineItems();
        if (lineItems == null) {
            lineItems = new ArrayList<>();
            order.setLineItems(lineItems);
        }
        return lineItems;
    }

    private Optional<OrderLineItem> findOrderLineItem(List<OrderLineItem> lineItems, String itemName) {
        return lineItems.stream()
                .filter(lineItem -> itemName.equals(lineItem.getName()))
                .findFirst();
    }

    private void incrementItemQuantity(Order order, OrderLineItem lineItem) {
        BigDecimal itemPrice = lineItem.getPrice();
        lineItem.setQty(lineItem.getQty() + 1);
        lineItem.setExtendedPrice(lineItem.getExtendedPrice().add(itemPrice));
        increaseOrderAmounts(order, itemPrice);
    }

    private void increaseOrderAmounts(Order order, BigDecimal itemPrice) {
        double taxRate = salesTaxRateRepository.getSalesTaxRate().getRate();
        order.setSubTotal(order.getSubTotal().add(itemPrice));
        order.setTotalTax(order.getSubTotal()
                .multiply(new BigDecimal(taxRate))
                .setScale(2, RoundingMode.DOWN));
        order.setGrandTotal(order.getSubTotal().add(order.getTotalTax()));
    }

    private void addNewItem(Order order, List<OrderLineItem> lineItems, String itemName) {
        Item item = findItem(itemName);
        BigDecimal itemPrice = item.getPrice();
        OrderLineItem lineItem = new OrderLineItem();
        lineItem.setName(item.getName());
        lineItem.setQty(1);
        lineItem.setPrice(itemPrice);
        lineItem.setExtendedPrice(itemPrice);
        lineItems.add(lineItem);
        increaseOrderAmounts(order, itemPrice);
    }

    private Item findItem(String itemName) {
        return itemRepository.getItems().stream()
                .filter(item -> itemName.equals(item.getName()))
                .findAny()
                .get();
    }
}
