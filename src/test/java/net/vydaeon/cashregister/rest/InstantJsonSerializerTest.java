package net.vydaeon.cashregister.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.time.Instant;

import static org.mockito.Mockito.verify;

/**
 * Unit tests for {@link InstantJsonSerializer}.
 *
 * @author Brad Bottjen
 */
public class InstantJsonSerializerTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private JsonGenerator gen;

    @Mock
    private SerializerProvider serializers;

    private InstantJsonSerializer serializer = new InstantJsonSerializer();

    @Test
    public void serialize() throws Exception {
        String string = "2016-08-06T13:48:31.123456789Z";
        Instant instant = Instant.parse(string);
        serializer.serialize(instant, gen, serializers);
        verify(gen).writeString(string);
    }
}
