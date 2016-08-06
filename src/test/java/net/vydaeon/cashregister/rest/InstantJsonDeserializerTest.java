package net.vydaeon.cashregister.rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.time.Instant;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link InstantJsonDeserializer}.
 *
 * @author Brad Bottjen
 */
public class InstantJsonDeserializerTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private JsonParser p;

    @Mock
    private DeserializationContext ctxt;

    private InstantJsonDeserializer deserializer = new InstantJsonDeserializer();

    @Test
    public void deserialize() throws Exception {
        String string = "2016-08-06T13:48:31.123456789Z";
        when(p.nextTextValue()).thenReturn(string);

        Instant expected = Instant.parse(string);
        Instant actual = deserializer.deserialize(p, ctxt);
        assertThat(actual, is(expected));
    }
}
