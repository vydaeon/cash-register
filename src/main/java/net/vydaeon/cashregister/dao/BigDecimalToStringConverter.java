package net.vydaeon.cashregister.dao;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.math.BigDecimal;

/**
 * {@link Converter} from {@link BigDecimal} to {@link String}.
 *
 * @author Brad Bottjen
 */
@WritingConverter
class BigDecimalToStringConverter implements Converter<BigDecimal, String> {

    static final BigDecimalToStringConverter INSTANCE = new BigDecimalToStringConverter();

    private BigDecimalToStringConverter() {
    }

    @Override
    public String convert(BigDecimal source) {
        return source.toString();
    }
}
