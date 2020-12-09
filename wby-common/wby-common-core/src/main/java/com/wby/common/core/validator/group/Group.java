package com.wby.common.core.validator.group;

import javax.validation.GroupSequence;

/**
 * @Description 定义检验顺序，如果AddGroup组失败，则UpdateGroup不会再检验
 * @Author wby
 * @Date 2019/11/4 11:24
 */
@GroupSequence({CreateGroup.class, UpdateGroup.class})
public interface Group {
}
