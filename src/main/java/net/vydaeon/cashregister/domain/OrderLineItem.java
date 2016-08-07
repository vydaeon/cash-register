package net.vydaeon.cashregister.domain;

import java.math.BigDecimal;

/**
 * Represents an line item in an {@link Order}.
 *
 * @author Brad Bottjen
 */
public class OrderLineItem {

    private String name;
    private int qty;
    private BigDecimal price;
    private BigDecimal extendedPrice;

    /**
     * @return the item name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the item name.
     *
     * @param name The item name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the item quantity.
     */
    public int getQty() {
        return qty;
    }

    /**
     * Sets the item quantity.
     *
     * @param qty The item quantity.
     */
    public void setQty(int qty) {
        this.qty = qty;
    }

    /**
     * @return the (per item) price.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the (per item) price.
     *
     * @param price The (per item) price.
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the extended price for the quantity of item.
     */
    public BigDecimal getExtendedPrice() {
        return extendedPrice;
    }

    /**
     * Sets the extended price for the quantity of item.
     *
     * @param extendedPrice The extended price.
     */
    public void setExtendedPrice(BigDecimal extendedPrice) {
        this.extendedPrice = extendedPrice;
    }
}
