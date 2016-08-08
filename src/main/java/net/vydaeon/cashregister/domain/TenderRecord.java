package net.vydaeon.cashregister.domain;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Represents a record of payment tendered for an order.
 *
 * @author Brad Bottjen
 */
public class TenderRecord {

    private BigDecimal amountTendered;
    private BigDecimal changeGiven;
    private Instant timestamp;

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

    /**
     * @return the change due from the amount tendered.
     */
    public BigDecimal getChangeGiven() {
        return changeGiven;
    }

    /**
     * Sets the change due from the amount tendered.
     *
     * @param changeGiven The change due from the amount tendered.
     */
    public void setChangeGiven(BigDecimal changeGiven) {
        this.changeGiven = changeGiven;
    }

    /**
     * @return the timestamp of the payment.
     */
    public Instant getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of the payment.
     *
     * @param timestamp The timestamp of the payment.
     */
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
