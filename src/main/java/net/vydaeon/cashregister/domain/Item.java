package net.vydaeon.cashregister.domain;

import java.math.BigDecimal;

/**
 * Represents an item available on the menu.
 *
 * @author Brad Bottjen
 */
public class Item {

    private String name;
    private BigDecimal price;

    /**
     * Full constructor.
     *
     * @param name  The item name.
     * @param price The item price, as a {@link BigDecimal}.
     */
    public Item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Default constructor.
     */
    public Item() {
    }

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
     * @return the item price, as a {@link BigDecimal}.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the item price.
     *
     * @param price The item price, as a {@link BigDecimal}.
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
