package com.wby.server.base.system.mapper;

import com.wby.api.base.system.entity.SysFile;
import com.wby.common.core.mapper.SuperMapper;

import java.util.List;
import java.util.Map;

/**
 * 附件表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
public interface SysFileMapper extends SuperMapper<SysFile> {

    /**
     * 通过tableId和recordId获取相关附件信息
     *
     * @param params
     * @return
     */
    List<SysFile> selectFileListByTableIdAndRecordId(Map<String, Object> params);


}
