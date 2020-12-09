package com.wby.server.base.system.mapper;

import com.wby.api.base.system.entity.SysRole;
import com.wby.api.base.system.vo.RoleVo;
import com.wby.common.core.mapper.SuperMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 角色表
 *
 * @author wby
 * @date 2018-12-11 11:35:15
 */
public interface SysRoleMapper extends SuperMapper<SysRole> {

    /**
     * 多表信息查询
     *
     * @param param
     * @return
     */
    List<SysRole> selectSysRoleList(@Param("param") Map<String, Object> param);

    RoleVo selectByUserId(@Param("userId") Long userId);

    RoleVo selectByRoleId(@Param("roleId") Long roleId);


}
