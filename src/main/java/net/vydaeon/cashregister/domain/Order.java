package net.vydaeon.cashregister.domain;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Represents an order made via the cash register application.
 *
 * @author Brad Bottjen
 */
@Document
public class Order {

    @Id
    private String id;

    @Field
    private Integer orderNumber;

    @Field
    private Instant timestamp;

    @Field
    private BigDecimal subTotal;

    @Field
    private BigDecimal totalTax;

    @Field
    private BigDecimal grandTotal;

    /**
     * @return the order ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the order ID.
     *
     * @param id The order ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the order number (1 - 100).
     */
    public Integer getOrderNumber() {
        return orderNumber;
    }

    /**
     * Sets the order number (1 - 100).
     *
     * @param orderNumber The order number (1 - 100).
     */
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return the order timestamp.
     */
    public Instant getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the order timestamp.
     *
     * @param timestamp The order timestamp.
     */
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the order sub-total.
     */
    public BigDecimal getSubTotal() {
        return subTotal;
    }

    /**
     * Sets the order sub-total.
     *
     * @param subTotal The order sub-total.
     */
    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    /**
     * @return the order total tax.
     */
    public BigDecimal getTotalTax() {
        return totalTax;
    }

    /**
     * Sets the order total tax.
     *
     * @param totalTax The order total tax.
     */
    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    /**
     * @return the order grand total.
     */
    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    /**
     * Sets the order grand total.
     *
     * @param grandTotal The order grand total.
     */
    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }
}
