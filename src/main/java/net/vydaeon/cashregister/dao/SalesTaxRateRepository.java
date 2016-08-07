package net.vydaeon.cashregister.dao;

import net.vydaeon.cashregister.domain.SalesTaxRate;

/**
 * Repository interface for {@link SalesTaxRate}.
 *
 * @author Brad Bottjen
 */
public interface SalesTaxRateRepository {

    /**
     * @return the {@link SalesTaxRate} to be applied to orders.
     */
    SalesTaxRate getSalesTaxRate();
}
