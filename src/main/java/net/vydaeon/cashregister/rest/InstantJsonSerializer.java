package net.vydaeon.cashregister.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.Instant;

/**
 * {@link JsonSerializer} for {@link Instant}s.
 *
 * @author Brad Bottjen
 */
@JsonComponent
class InstantJsonSerializer extends JsonSerializer<Instant> {

    @Override
    public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String stringValue = value.toString();
        gen.writeString(stringValue);
    }
}
