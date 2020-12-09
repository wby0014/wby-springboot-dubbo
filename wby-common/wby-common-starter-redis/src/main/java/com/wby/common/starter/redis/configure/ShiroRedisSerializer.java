package com.wby.common.starter.redis.configure;

import org.apache.commons.lang3.SerializationException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author wby
 * @Descriptin redis序列化对象
 * @Date 2018年5月1日 下午3:13:58
 */
public class ShiroRedisSerializer implements RedisSerializer<Object> {

    static final byte[] EMPTY_ARRAY = new byte[0];
    private Converter<Object, byte[]> serializer = new SerializingConverter();
    private Converter<byte[], Object> deserializer = new DeserializingConverter();

    public Object deserialize(byte[] bytes) {
        if (isEmpty(bytes)) {
            return null;
        }
        try {
            return deserializer.convert(bytes);
        } catch (Exception ex) {
            throw new SerializationException("Cannot deserialize", ex);
        }
    }

    public byte[] serialize(Object object) {
        if (object == null) {
            return EMPTY_ARRAY;
        }
        try {
            return serializer.convert(object);
        } catch (Exception ex) {
            return EMPTY_ARRAY;
        }
    }

    private boolean isEmpty(byte[] data) {
        return (data == null || data.length == 0);
    }
}
