package net.vydaeon.cashregister.dao;

import net.vydaeon.cashregister.domain.SalesTaxRate;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Unit tests for {@link SalesTaxRateRepositoryPropertiesImpl}.
 *
 * @author Brad Bottjen
 */
public class SalesTaxRateRepositoryPropertiesImplTest {

    private static final String DESCRIPTION = "description";
    private static final double RATE = 0.0123;

    private SalesTaxRateRepositoryPropertiesImpl repository;

    @Before
    public void before() {
        SalesTaxRateProperties properties = new SalesTaxRateProperties();
        repository = new SalesTaxRateRepositoryPropertiesImpl(properties);
        properties.setDescription(DESCRIPTION);
        properties.setRate(RATE);
    }

    @Test
    public void getSalesTaxRate() {
        SalesTaxRate salesTaxRate = repository.getSalesTaxRate();
        assertThat(salesTaxRate, is(notNullValue()));
        assertThat(salesTaxRate.getDescription(), is(DESCRIPTION));
        assertThat(salesTaxRate.getRate(), is(RATE));
    }
}
