package com.wby.api.base.system.vo;


import com.wby.api.base.system.entity.SysResource;
import com.wby.api.base.system.entity.SysRole;

import java.io.Serializable;
import java.util.List;


/**
 * @description：UserVo
 * @author：wby
 * @date 2018年5月6日 上午9:55:46
 */
public class RoleVo extends SysRole implements Serializable {

    // 拥有的权限列表
    private List<SysResource> permissions;

    public List<SysResource> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysResource> permissions) {
        this.permissions = permissions;
    }


}
