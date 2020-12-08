package com.wby.common.core.jackson.serializer;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * <p>
 * Jackson Date反序列化器
 * </p>
 *
 * @author JacksonTu
 * @date 2020-04-02
 */
public class JacksonDateSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        String string = null;
        if (date != null) {
            string = DateUtil.format(date, DatePattern.NORM_DATETIME_PATTERN);
        }
        jsonGenerator.writeString(string);
    }
}
