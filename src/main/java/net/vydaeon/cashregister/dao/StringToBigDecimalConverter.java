package net.vydaeon.cashregister.dao;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.math.BigDecimal;

/**
 * {@link Converter} from {@link String} to {@link BigDecimal}.
 *
 * @author Brad Bottjen
 */
@ReadingConverter
class StringToBigDecimalConverter implements Converter<String, BigDecimal> {

    static final StringToBigDecimalConverter INSTANCE = new StringToBigDecimalConverter();

    private StringToBigDecimalConverter() {
    }

    @Override
    public BigDecimal convert(String source) {
        return new BigDecimal(source);
    }
}
