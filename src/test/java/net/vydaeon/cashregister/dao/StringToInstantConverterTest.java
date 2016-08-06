package net.vydaeon.cashregister.dao;

import org.junit.Test;

import java.time.Instant;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit tests for {@link StringToInstantConverter}.
 *
 * @author Brad Bottjen
 */
public class StringToInstantConverterTest {

    @Test
    public void convert() {
        Instant expected = Instant.now();
        String string = expected.toString();
        Instant actual = StringToInstantConverter.INSTANCE.convert(string);
        assertThat(actual, is(expected));
    }
}
