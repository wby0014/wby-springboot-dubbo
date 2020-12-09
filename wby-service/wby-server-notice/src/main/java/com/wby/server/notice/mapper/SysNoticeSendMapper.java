package com.wby.server.notice.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wby.api.notice.dto.NoticeSendDto;
import com.wby.api.notice.entity.SysNotice;
import com.wby.api.notice.entity.SysNoticeSend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户通告阅读标记表 Mapper
 *
 * @author wby
 * @date 2020-10-12 15:03:04
 */
public interface SysNoticeSendMapper extends BaseMapper<SysNoticeSend> {

    List<Long> listByUserId(@Param("userId") Long userId);

    /**
     * 获取我的消息
     *
     * @param page
     * @param noticeSendDto
     * @return
     */
    IPage<SysNotice> pageMyNoticeSend(IPage<SysNotice> page, @Param("ew") NoticeSendDto noticeSendDto);

}
