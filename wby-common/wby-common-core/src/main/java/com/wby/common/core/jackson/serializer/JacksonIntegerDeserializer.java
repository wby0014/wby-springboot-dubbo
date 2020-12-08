package com.wby.common.core.jackson.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.wby.common.core.jackson.converter.StringToIntegerUtil;

import java.io.IOException;

/**
 * @author JacksonTu
 * @date 2020-04-02
 */
public class JacksonIntegerDeserializer extends JsonDeserializer<Integer> {

    @Override
    public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String string = jsonParser.getText();
        return StringToIntegerUtil.convert(string);
    }
}
