/*
 * Copyright 2019-2029 JacksonTu(https://github.com/JacksonTu)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wby.common.core.jackson.converter;

import org.apache.commons.lang3.StringUtils;

/**
 * <code>
 * <pre>
 * 空字符串("")转换成Integer的null
 *
 * </pre>
 * </code>
 *
 * @author JacksonTu
 * @date 2020-04-02
 */
public class StringToIntegerUtil {

    public static Integer convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        Integer i = Integer.parseInt(source);
        return i;
    }
}
