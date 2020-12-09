package com.wby.web.base.system.controller;

import com.google.common.collect.Lists;
import com.wby.api.base.system.dto.RoleDto;
import com.wby.api.base.system.entity.SysRole;
import com.wby.api.base.system.entity.SysRoleResource;
import com.wby.api.base.system.service.ISysRoleResourceService;
import com.wby.api.base.system.service.ISysRoleService;
import com.wby.common.core.api.CommonResult;
import com.wby.common.core.constant.CommonConstant;
import com.wby.common.core.validator.ValidatorUtil;
import com.wby.common.core.vo.PageVo;
import com.wby.common.core.vo.TreeVo;
import com.wby.web.base.shiro.ShiroUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 角色管理
 * @Author wby
 * @Date 2018/12/13 15:12
 */
@Api(value = "角色管理接口", tags = {" 角色管理接口"})
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

    @DubboReference
    private ISysRoleService sysRoleService;
    @DubboReference
    private ISysRoleResourceService sysRoleResourceService;

    /**
     * 角色列表
     */
    @ApiOperation(value = "角色列表", notes = "角色列表")
    @GetMapping("/list")
    @RequiresPermissions("sys/role/list")
    public CommonResult<PageVo<SysRole>> list(RoleDto roleDto) {
        //如果不是超级管理员，则只查询自己创建的角色列表
        if (ShiroUtil.getUser().getId() != CommonConstant.SUPER_ADMIN) {
            roleDto.setCreateUserId(ShiroUtil.getUser().getId());
        }
        PageVo<SysRole> page = sysRoleService.pageList(roleDto);
        return CommonResult.success(page);
    }

    /**
     * 角色选择列表
     */
    @ApiOperation(value = "角色选择列表", notes = "角色选择列表")
    @GetMapping("/select")
    @RequiresPermissions("sys/role/select")
    public CommonResult<List<SysRole>> select() {
        Map<String, Object> map = new HashMap<>();
        //如果不是超级管理员，则只查询自己所拥有的角色列表
        if (ShiroUtil.getUser().getId() != CommonConstant.SUPER_ADMIN) {
            map.put("createUserId", ShiroUtil.getUser().getId());
        }
        List<SysRole> list = sysRoleService.selectSysRoleList(map);

        return CommonResult.success(list);
    }

    /**
     * 角色信息
     */
    @ApiOperation(value = "角色信息", notes = "角色信息")
    @ApiImplicitParam(paramType = "path", name = "roleId", value = "主键ID", dataType = "Integer", required = true)
    @GetMapping("/info/{roleId}")
    @RequiresPermissions("sys/role/info")
    public CommonResult<SysRole> info(@PathVariable("roleId") Long roleId) {
        SysRole role = sysRoleService.getById(roleId);
        //查询角色对应的菜单
        List<Long> resourceIdList = sysRoleResourceService.selectResourceIdListByRoleId(roleId);
        role.setResourceIdList(resourceIdList);
        List<SysRoleResource> roleResourceList = sysRoleResourceService.selectResourceNodeListByRoleId(roleId);
        List<TreeVo> treeVoList = Lists.newArrayList();
        if (!roleResourceList.isEmpty()) {
            roleResourceList.forEach(roleResource -> {
                TreeVo treeVo = new TreeVo();
                treeVo.setId(roleResource.getResourceId().toString());
                treeVo.setLabel(roleResource.getResource().getName());
                treeVoList.add(treeVo);
            });
        }
        role.setResourceNodeList(treeVoList);
        return CommonResult.success(role);
    }

    /**
     * 保存角色
     */
    @ApiOperation(value = "保存角色", notes = "保存角色")
    @PostMapping("/save")
    @RequiresPermissions("sys/role/save")
    public CommonResult save(@RequestBody SysRole role) {
        ValidatorUtil.validateEntity(role);
        role.setCreateTime(new Date());
        role.setCreateUserId(ShiroUtil.getUser().getId());
        sysRoleService.saveByVo(role);

        return CommonResult.success("");
    }

    /**
     * 修改角色
     */
    @ApiOperation(value = "修改角色", notes = "修改角色")
    @PostMapping("/update")
    @RequiresPermissions("sys/role/update")
    public CommonResult update(@RequestBody SysRole role) {
        ValidatorUtil.validateEntity(role);
        role.setUpdateTime(new Date());
        role.setCreateUserId(ShiroUtil.getUser().getId());
        sysRoleService.updateByVo(role);

        return CommonResult.success("");
    }

    /**
     * 删除角色
     */
    @ApiOperation(value = "删除角色", notes = "删除角色")
    @ApiImplicitParam(name = "roleIds", allowMultiple = true, dataType = "Integer", required = true, value = "角色ID数组", paramType = "query")
    @PostMapping("/delete")
    @RequiresPermissions("sys/role/delete")
    public CommonResult delete(@RequestBody Long[] roleIds) {
        sysRoleService.deleteBatch(roleIds);
        return CommonResult.success("");
    }

}
