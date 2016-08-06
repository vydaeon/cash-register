package net.vydaeon.cashregister.dao;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.Instant;

/**
 * {@link Converter} from {@link String} to {@link Instant}.
 *
 * @author Brad Bottjen
 */
@ReadingConverter
class StringToInstantConverter implements Converter<String, Instant> {

    static final StringToInstantConverter INSTANCE = new StringToInstantConverter();

    private StringToInstantConverter() {
    }

    @Override
    public Instant convert(String source) {
        return Instant.parse(source);
    }
}
