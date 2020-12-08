package com.wby.common.core.jackson.converter;


import org.apache.commons.lang3.StringUtils;

/**
 * <code>
 * <pre>
 * 空字符串("")转换成Double的null
 *
 * </pre>
 * </code>
 *
 * @author JacksonTu
 * @date 2020-04-02
 */
public class StringToDoubleUtil {

    public static Double convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        Double d = Double.parseDouble(source);
        return d;
    }
}
