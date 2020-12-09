package com.wby.web.base.system.controller;

import com.wby.api.base.system.dto.UserDto;
import com.wby.api.base.system.entity.SysUser;
import com.wby.api.base.system.service.ISysUserEnterpriseService;
import com.wby.api.base.system.service.ISysUserRoleService;
import com.wby.api.base.system.service.ISysUserService;
import com.wby.api.base.system.vo.UserVo;
import com.wby.common.core.api.CommonResult;
import com.wby.common.core.constant.CommonConstant;
import com.wby.common.core.exception.BaseException;
import com.wby.common.core.validator.group.CreateGroup;
import com.wby.common.core.validator.group.UpdateGroup;
import com.wby.common.core.vo.LoginUserVo;
import com.wby.common.core.vo.PageVo;
import com.wby.common.core.vo.SelectVo;
import com.wby.web.base.shiro.ShiroUtil;
import com.wby.web.base.system.dto.PasswordDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description 用户表接口
 * @Author wby
 * @Date 2018/12/13 11:42
 */
@Slf4j
@Api(value = "用户表接口", tags = {"用户表接口"})
@RestController
@RequestMapping("sys/user")
public class SysUserController {

    @DubboReference
    private ISysUserService sysUserService;
    @DubboReference
    private ISysUserRoleService sysUserRoleService;
    @DubboReference
    private ISysUserEnterpriseService sysUserEnterpriseService;


    /**
     * 所有用户列表
     */
    @ApiOperation(value = "用户列表", notes = "用户列表")
    @GetMapping("/list")
    @RequiresPermissions("sys/user/list")
    public CommonResult<PageVo<UserVo>> list(UserDto userDto) {
        //只有超级管理员，才能查看所有管理员列表
        Long currUserId = ShiroUtil.getUser().getId();
        if (currUserId != CommonConstant.SUPER_ADMIN) {
            //非管理员查看自己和自己创建的账号
            userDto.setCreateUserId(currUserId);
            userDto.setId(currUserId);
        }
        PageVo<UserVo> page = sysUserService.pageList(userDto);

        return CommonResult.success(page);
    }

    /**
     * 修改登录用户密码
     */
    @ApiOperation(value = "修改登录用户密码", notes = "修改登录用户密码")
    @PostMapping("/password")
    public CommonResult password(@RequestBody PasswordDto form) {
        if (StringUtils.isBlank(form.getNewPassword())) {
            return CommonResult.failed("新密码不为能空");
        }
        SysUser user = sysUserService.getById(ShiroUtil.getUser().getId());
        String password = ShiroUtil.md5(form.getPassword(), user.getLoginName() + user.getSalt());
        if (!user.getPassword().equals(password)) {
            return CommonResult.failed("原密码不正确");
        }
        String newPassword = ShiroUtil.md5(form.getNewPassword(), user.getLoginName() + user.getSalt());
        user.setPassword(newPassword);
        user.setUpdateTime(new Date());
        sysUserService.updateById(user);
        return CommonResult.success("密码修改成功");
    }

    /**
     * 登录用户信息
     */
    @ApiOperation(value = "登录用户信息", notes = "登录用户信息")
    @GetMapping("/info")
    public CommonResult<LoginUserVo> info() {

        return CommonResult.success(ShiroUtil.getUser());
    }

    /**
     * 用户信息
     */
    @ApiOperation(value = "用户信息", notes = "用户信息")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "主键ID", dataType = "Integer", required = true)
    @GetMapping("/info/{userId}")
    @RequiresPermissions("sys/user/info")
    public CommonResult<SysUser> info(@PathVariable("userId") Long userId) {
        SysUser user = sysUserService.getById(userId);
        List<Long> roleIdList = sysUserRoleService.selectRoleIdListByUserId(userId);
        user.setRoleIdList(roleIdList);
        List<Long> enterpriseIdList = sysUserEnterpriseService.selectEnterpriseIdByUserId(userId);
        user.setEnterpriseIdList(enterpriseIdList);
        return CommonResult.success(user);

    }

    /**
     * 保存用户
     */
    @ApiOperation(value = "保存用户", notes = "保存用户")
    @PostMapping("/save")
    @RequiresPermissions("sys/user/save")
    public CommonResult save(@RequestBody @Validated(CreateGroup.class) SysUser user) {
        try {
            SysUser sysUser = sysUserService.selectByLoginName(user.getLoginName());
            if (!ObjectUtils.isEmpty(sysUser)) {
                return CommonResult.failed("登录名已存在");
            }
            String salt = ShiroUtil.getRandomSalt(16);
            user.setSalt(salt);
            String pwd = ShiroUtil.md5(user.getPassword(), user.getLoginName() + salt);
            user.setPassword(pwd);
            user.setCreateTime(new Date());
            user.setCreateUserId(ShiroUtil.getUser().getId());
            sysUserService.saveByVo(user);
            return CommonResult.success("添加成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BaseException("运行异常，请联系管理员");
        }
    }

    /**
     * 修改用户
     */
    @PostMapping("/update")
    @RequiresPermissions("sys/user/update")
    public CommonResult update(@RequestBody @Validated(UpdateGroup.class) SysUser user) {
        try {
            if (StringUtils.isNotBlank(user.getPassword())) {
                String salt = ShiroUtil.getRandomSalt(16);
                user.setSalt(salt);
                String pwd = ShiroUtil.md5(user.getPassword(), user.getLoginName() + salt);
                user.setPassword(pwd);
            } else {
                user.setPassword(null);
            }
            user.setUpdateTime(new Date());
            user.setCreateUserId(ShiroUtil.getUser().getId());
            sysUserService.updateByVo(user);
            return CommonResult.success("修改成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BaseException("运行异常，请联系管理员");
        }
    }

    /**
     * 删除用户
     */
    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiImplicitParam(name = "userIds", allowMultiple = true, dataType = "Integer", required = true, value = "用户ID数组", paramType = "query")
    @PostMapping("/delete")
    @RequiresPermissions("sys/user/delete")
    public CommonResult delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains(userIds, CommonConstant.SUPER_ADMIN)) {
            return CommonResult.failed("系统管理员不能删除");
        }
        if (ArrayUtils.contains(userIds, ShiroUtil.getUser().getId())) {
            return CommonResult.failed("当前用户不能删除");
        }
        sysUserService.deleteBatch(userIds);
        return CommonResult.success("");
    }

    /**
     * 用户选择树
     *
     * @return
     */
    @ApiOperation(value = "用户选择树", notes = "用户选择树")
    @GetMapping("/getUserTree")
    public CommonResult<List<SelectVo>> getUserTree() {
        try {
            List<SelectVo> nodeList = new ArrayList<>();
            List<SysUser> list = sysUserService.list();
            list.forEach(baseUser -> {
                SelectVo node = new SelectVo();
                node.setLabel(baseUser.getName());
                node.setValue(baseUser.getId().toString());
                nodeList.add(node);
            });
            return CommonResult.success(nodeList);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BaseException("运行异常，请联系管理员");
        }
    }
}
