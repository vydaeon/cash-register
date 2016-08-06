package net.vydaeon.cashregister.dao;

import org.junit.Test;

import java.time.Instant;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit tests for {@link InstantToStringConverter}.
 *
 * @author Brad Bottjen
 */
public class InstantToStringConverterTest {

    @Test
    public void convert() {
        String expected = "2016-08-06T13:48:31.123456789Z";
        Instant instant = Instant.parse(expected);
        String actual = InstantToStringConverter.INSTANCE.convert(instant);
        assertThat(actual, is(expected));
    }
}
