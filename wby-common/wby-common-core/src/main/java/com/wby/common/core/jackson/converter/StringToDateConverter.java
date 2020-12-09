package com.wby.common.core.jackson.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * <code>
 * 日期转换器,将请求参数的日期字符串转换成java.util.Date类型
 * </code>
 *
 * @author wby
 * @date 2020-04-02
 */
public class StringToDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        return StringToDateUtil.convert(source);
    }
}
