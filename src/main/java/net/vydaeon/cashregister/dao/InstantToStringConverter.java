package net.vydaeon.cashregister.dao;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.time.Instant;

/**
 * {@link Converter} from {@link Instant} to {@link String}.
 *
 * @author Brad Bottjen
 */
@WritingConverter
class InstantToStringConverter implements Converter<Instant, String> {

    static final InstantToStringConverter INSTANCE = new InstantToStringConverter();

    private InstantToStringConverter() {
    }

    @Override
    public String convert(Instant source) {
        return source.toString();
    }
}
