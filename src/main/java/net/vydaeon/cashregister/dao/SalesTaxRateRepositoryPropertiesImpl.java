package net.vydaeon.cashregister.dao;

import net.vydaeon.cashregister.domain.SalesTaxRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * {@link SalesTaxRateRepository} implementation using Spring Boot properties.
 *
 * @author Brad Bottjen
 */
@Repository
class SalesTaxRateRepositoryPropertiesImpl implements SalesTaxRateRepository {

    private final SalesTaxRateProperties salesTaxRateProperties;

    @Autowired
    SalesTaxRateRepositoryPropertiesImpl(SalesTaxRateProperties salesTaxRateProperties) {
        this.salesTaxRateProperties = salesTaxRateProperties;
    }

    @Override
    public SalesTaxRate getSalesTaxRate() {
        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setDescription(salesTaxRateProperties.getDescription());
        salesTaxRate.setRate(salesTaxRateProperties.getRate());
        return salesTaxRate;
    }
}
