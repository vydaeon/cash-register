package net.vydaeon.cashregister.dao;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit tests for {@link BigDecimalToStringConverter}.
 *
 * @author Brad Bottjen
 */
public class BigDecimalToStringConverterTest {

    @Test
    public void convert() {
        String expected = "1.12358";
        BigDecimal bigDecimal = new BigDecimal(expected);
        String actual = BigDecimalToStringConverter.INSTANCE.convert(bigDecimal);
        assertThat(actual, is(expected));
    }
}
