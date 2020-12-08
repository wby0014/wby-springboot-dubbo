package com.wby.common.core.jackson.converter;

import org.springframework.core.convert.converter.Converter;

/**
 * <code>
 *
 * </code>
 *
 * @author JacksonTu
 * @date 2020-04-02
 */
public class StringToDoubleConverter implements Converter<String, Double> {

    @Override
    public Double convert(String source) {
        return StringToDoubleUtil.convert(source);
    }
}
