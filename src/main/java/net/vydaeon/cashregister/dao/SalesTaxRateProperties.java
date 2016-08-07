package net.vydaeon.cashregister.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * {@link ConfigurationProperties} for sales tax rate.
 *
 * @author Brad Bottjen
 */
@Component
class SalesTaxRateProperties {

    @Value("${salesTaxRate.description}")
    private String description;

    @Value("${salesTaxRate.rate}")
    private double rate;

    /**
     * @return the description for the tax rate.
     */
    String getDescription() {
        return description;
    }

    /**
     * Sets the description for the tax rate.
     *
     * @param description The description for the tax rate.
     */
    void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the tax rate.
     */
    double getRate() {
        return rate;
    }

    /**
     * Sets the tax rate.
     *
     * @param rate The tax rate.
     */
    void setRate(double rate) {
        this.rate = rate;
    }
}
