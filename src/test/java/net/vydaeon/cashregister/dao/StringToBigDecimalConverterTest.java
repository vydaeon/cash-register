package net.vydaeon.cashregister.dao;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit tests for {@link StringToBigDecimalConverter}.
 *
 * @author Brad Bottjen
 */
public class StringToBigDecimalConverterTest {

    @Test
    public void convert() {
        BigDecimal expected = new BigDecimal(ThreadLocalRandom.current().nextDouble());
        String string = expected.toString();
        BigDecimal actual = StringToBigDecimalConverter.INSTANCE.convert(string);
        assertThat(actual, is(expected));
    }
}
