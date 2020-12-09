package com.wby.common.core.jackson.deserializer;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * Jackson LocaDateTime反序列化器
 * </p>
 *
 * @author wby
 * @date 2020-04-02
 */
public class JacksonLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        String string = jp.getText();
        if (StringUtils.isBlank(string)) {
            return null;
        }
        return LocalDateTime.parse(string, DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN));
    }

}
