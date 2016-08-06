package net.vydaeon.cashregister.rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.Instant;

/**
 * {@link JsonDeserializer} for {@link Instant}s.
 *
 * @author Brad Bottjen
 */
@JsonComponent
class InstantJsonDeserializer extends JsonDeserializer<Instant> {

    @Override
    public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String textValue = p.nextTextValue();
        return Instant.parse(textValue);
    }
}
