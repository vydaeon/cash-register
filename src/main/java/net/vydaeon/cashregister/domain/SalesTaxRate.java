package net.vydaeon.cashregister.domain;

/**
 * Represents the sales tax rate to be applied to {@link Order}s.
 *
 * @author Brad Bottjen
 */
public class SalesTaxRate {

    private String description;
    private double rate;

    /**
     * @return the description for the tax rate.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description for the tax rate.
     *
     * @param description The description for the tax rate.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the tax rate.
     */
    public double getRate() {
        return rate;
    }

    /**
     * Sets the tax rate.
     *
     * @param rate The tax rate.
     */
    public void setRate(double rate) {
        this.rate = rate;
    }
}
