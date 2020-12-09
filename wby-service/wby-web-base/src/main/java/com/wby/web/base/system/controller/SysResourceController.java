package com.wby.web.base.system.controller;

import com.wby.api.base.system.entity.SysResource;
import com.wby.api.base.system.service.ISysResourceService;
import com.wby.api.base.system.service.ISysUserService;
import com.wby.api.base.system.vo.MenuVo;
import com.wby.common.core.api.CommonResult;
import com.wby.common.core.constant.CommonConstant;
import com.wby.common.core.exception.BaseException;
import com.wby.web.base.shiro.ShiroUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Description 菜单管理接口
 * @Author wby
 * @Date 2018/12/11 14:21
 */
@Api(value = "菜单管理接口", tags = {"菜单管理接口"})
@RestController
@RequestMapping("sys/menu")
public class SysResourceController {
    @DubboReference
    private ISysResourceService sysResourceService;
    @DubboReference
    private ISysUserService sysUserService;

    /**
     * 导航菜单
     */
    @ApiOperation(value = "导航菜单", notes = "导航菜单")
    @GetMapping("/nav")
    public CommonResult<MenuVo> nav() {
        Long userId = ShiroUtil.getUser().getId();
        List<SysResource> menuList = sysResourceService.selectUserResourceListByUserId(userId);
        Set<String> permissions = sysUserService.selectUserPermissions(userId);
        MenuVo menuVo = new MenuVo();
        menuVo.setMenuList(menuList);
        menuVo.setPermissions(permissions);
        return CommonResult.success(menuVo);
    }

    /**
     * 所有菜单列表
     */
    @ApiOperation(value = "所有菜单列表", notes = "所有菜单列表")
    @GetMapping("/list")
    @RequiresPermissions("sys/menu/list")
    public CommonResult<List<SysResource>> list() {
        Map<String, Object> params = new HashMap<>();
        List<SysResource> menuList = sysResourceService.selectResourceList(params);
        return CommonResult.success(menuList);
    }

    /**
     * 菜单信息
     */
    @ApiOperation(value = "菜单信息", notes = "菜单信息")
    @ApiImplicitParam(paramType = "path", name = "menuId", value = "主键ID", dataType = "Integer", required = true)
    @GetMapping("/info/{menuId}")
    @RequiresPermissions("sys/menu/info")
    public CommonResult<SysResource> info(@PathVariable("menuId") Long menuId) {
        SysResource sysResource = sysResourceService.getById(menuId);
        return CommonResult.success(sysResource);
    }

    /**
     * 保存菜单信息
     */
    @ApiOperation(value = "保存菜单信息", notes = "保存菜单信息")
    @PostMapping("/save")
    @RequiresPermissions("sys/menu/save")
    public CommonResult save(@RequestBody SysResource sysResource) {
        //数据校验
        verifyForm(sysResource);
        sysResource.setCreateTime(new Date());
        sysResource.setCreateUser(ShiroUtil.getUser().getLoginName());
        sysResourceService.save(sysResource);
        return CommonResult.success("");
    }

    /**
     * 修改菜单信息
     */
    @ApiOperation(value = "修改菜单信息", notes = "修改菜单信息")
    @PostMapping("/update")
    @RequiresPermissions("sys/menu/update")
    public CommonResult update(@RequestBody SysResource sysResource) {
        //数据校验
        verifyForm(sysResource);
        sysResource.setUpdateTime(new Date());
        sysResource.setUpdateUser(ShiroUtil.getUser().getLoginName());
        sysResourceService.updateById(sysResource);
        return CommonResult.success("");
    }

    /**
     * 删除菜单信息
     */
    @ApiOperation(value = "删除菜单信息", notes = "删除菜单信息")
    @ApiImplicitParam(paramType = "path", name = "menuId", value = "主键ID", dataType = "Integer", required = true)
    @PostMapping("/delete/{menuId}")
    @RequiresPermissions("sys/menu/delete")
    public CommonResult delete(@PathVariable("menuId") long menuId) {
        if (menuId <= 31) {
            return CommonResult.failed("系统菜单，不能删除");
        }
        //判断是否有子菜单或按钮
        List<SysResource> menuList = sysResourceService.selectListByParentId(menuId);
        if (menuList.size() > 0) {
            return CommonResult.failed("请先删除子菜单或按钮");
        }
        sysResourceService.removeById(menuId);
        return CommonResult.success("");
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @ApiOperation(value = "选择菜单(添加、修改菜单)", notes = "选择菜单(添加、修改菜单)")
    @GetMapping("/select")
    @RequiresPermissions("sys/menu/select")
    public CommonResult<List<SysResource>> select() {
        //查询列表数据
        List<SysResource> menuList = sysResourceService.selectNotButtonList();
        //添加顶级菜单
        SysResource root = new SysResource();
        root.setId(0L);
        root.setName("顶级菜单");
        root.setParentId(0L);
        root.setOpen(true);
        menuList.add(root);
        return CommonResult.success(menuList);
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysResource sysResource) {
        if (StringUtils.isBlank(sysResource.getName())) {
            throw new BaseException("菜单名称不能为空");
        }
        if (sysResource.getParentId() == null) {
            throw new BaseException("上级菜单不能为空");
        }
        //菜单
        if (sysResource.getResourceType() == CommonConstant.MENU) {
            if (StringUtils.isBlank(sysResource.getUrl())) {
                throw new BaseException("菜单URL不能为空");
            }
        }
        //上级菜单类型
        int parentType = CommonConstant.CATALOG;
        if (sysResource.getParentId() != 0) {
            SysResource parentMenu = sysResourceService.getById(sysResource.getParentId());
            parentType = parentMenu.getResourceType();
        }
        //目录、菜单
        if (sysResource.getResourceType() == CommonConstant.CATALOG ||
                sysResource.getResourceType() == CommonConstant.MENU) {
            if (parentType != CommonConstant.CATALOG) {
                throw new BaseException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if (sysResource.getResourceType() == CommonConstant.BUTTON) {
            if (parentType != CommonConstant.MENU) {
                throw new BaseException("上级菜单只能为菜单类型");
            }
            return;
        }
    }
}
