package net.vydaeon.cashregister.rest;

import java.math.BigDecimal;

/**
 * DTO for a payment tendered for an order.
 *
 * @author Brad Bottjen
 */
public class TenderDto {

    private BigDecimal amountTendered;

    /**
     * @return the amount tendered for the order.
     */
    public BigDecimal getAmountTendered() {
        return amountTendered;
    }

    /**
     * Sets the amount tendered for the order.
     *
     * @param amountTendered The amount tendered for the order.
     */
    public void setAmountTendered(BigDecimal amountTendered) {
        this.amountTendered = amountTendered;
    }
}
