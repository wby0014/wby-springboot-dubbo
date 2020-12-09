package com.wby.web.base.enterprise.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wby.api.base.enterprise.dto.EnterpriseDepartmentDto;
import com.wby.api.base.enterprise.entity.EnterpriseDepartment;
import com.wby.api.base.enterprise.service.IEnterpriseDepartmentService;
import com.wby.api.base.enterprise.service.IEnterpriseService;
import com.wby.api.base.enterprise.vo.EnterpriseDepartmentVo;
import com.wby.common.core.api.CommonResult;
import com.wby.common.core.vo.LoginUserVo;
import com.wby.common.core.vo.SelectTreeVo;
import com.wby.web.base.shiro.ShiroUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * @Description 企业部门表
 * @Author wby
 * @Date 2018/12/17 11:29
 */
@Slf4j
@Api(value = "企业部门接口", tags = {"企业部门接口"})
@RestController
@RequestMapping("enterprise/enterpriseDepartment")
public class EnterpriseDepartmentController {
    @DubboReference(interfaceName = "IEnterpriseDepartmentService")
    private IEnterpriseDepartmentService enterpriseDepartmentService;

    @DubboReference(interfaceName = "IEnterpriseService")
    private IEnterpriseService enterpriseService;


    /**
     * 列表
     */
    @ApiOperation(value = "企业部门列表", notes = "企业部门列表")
    @GetMapping("/list")
    @RequiresPermissions("enterprise/enterpriseDepartment/list")
    public CommonResult<List<EnterpriseDepartmentVo>> treeGrid(EnterpriseDepartmentDto enterpriseDepartmentDto) {
        LoginUserVo loginUserVo = ShiroUtil.getUser();
        // 不是管理员
        if (loginUserVo.getUserType() != 0) {
            enterpriseDepartmentDto.setUserId(ShiroUtil.getUser().getId());
        }
        List<EnterpriseDepartmentVo> list = enterpriseDepartmentService.selectTreeGrid(enterpriseDepartmentDto);
        return CommonResult.success(list);
    }


    /**
     * 企业部门信息
     */
    @ApiOperation(value = "企业部门信息", notes = "企业部门信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "部门ID", dataType = "String", required = true)
    @GetMapping("/info/{id}")
    @RequiresPermissions("enterprise/enterpriseDepartment/info")
    public CommonResult<EnterpriseDepartmentVo> info(@PathVariable("id") String id) {
        EnterpriseDepartment enterpriseDepartment = enterpriseDepartmentService.getById(id);
        EnterpriseDepartmentVo enterpriseDepartmentVo = new EnterpriseDepartmentVo();
        BeanUtils.copyProperties(enterpriseDepartment, enterpriseDepartmentVo);
        return CommonResult.success(enterpriseDepartmentVo);
    }

    /**
     * 保存企业部门信息
     */
    @ApiOperation(value = "保存企业部门信息", notes = "保存企业部门信息")
    @PostMapping("/save")
    @RequiresPermissions("enterprise/enterpriseDepartment/save")
    public CommonResult save(@Valid @RequestBody EnterpriseDepartment enterpriseDepartment) {
        try {
            LoginUserVo loginUserVo = ShiroUtil.getUser();
            enterpriseDepartment.setCreateTime(new Date());
            enterpriseDepartment.setCreateUser(ShiroUtil.getUser().getLoginName());
            enterpriseDepartmentService.save(enterpriseDepartment);
            return CommonResult.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("运行异常，请联系管理员");
        }
    }

    /**
     * 修改企业部门信息
     */
    @ApiOperation(value = "修改企业部门信息", notes = "修改企业部门信息")
    @PostMapping("/update")
    @RequiresPermissions("enterprise/enterpriseDepartment/update")
    public CommonResult update(@Valid @RequestBody EnterpriseDepartment enterpriseDepartment) {
        try {
            enterpriseDepartment.setUpdateUser(ShiroUtil.getUser().getLoginName());
            enterpriseDepartment.setUpdateTime(new Date());
            enterpriseDepartmentService.updateById(enterpriseDepartment);
            return CommonResult.success("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("运行异常，请联系管理员");
        }

    }

    /**
     * 删除企业部门信息
     */
    @ApiOperation(value = "删除企业部门信息", notes = "删除企业部门信息")
    @ApiImplicitParam(paramType = "query", name = "ids", value = "部门ID数组", dataType = "String", required = true, allowMultiple = true)
    @PostMapping("/delete")
    @RequiresPermissions("enterprise/enterpriseDepartment/delete")
    public CommonResult deleteBatchIds(@RequestBody String[] ids) {
        try {
            List<String> idList = new ArrayList<>();
            Collections.addAll(idList, ids);
            if (idList != null && !idList.isEmpty()) {
                enterpriseDepartmentService.removeByIds(Arrays.asList(ids));
                for (String id : idList) {
                    QueryWrapper<EnterpriseDepartment> wrapper = new QueryWrapper<>();
                    wrapper.eq("parent_id", id);
                    enterpriseDepartmentService.remove(wrapper);
                }
                return CommonResult.success("删除成功");
            } else {
                return CommonResult.failed("删除失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("运行异常，请联系管理员");
        }
    }

    /**
     * 企业部门树形选择
     *
     * @param enterpriseId
     * @return
     */
    @ApiOperation(value = "企业部门树形选择", notes = "企业部门树形选择")
    @ApiImplicitParam(paramType = "query", name = "enterpriseId", value = "企业ID", dataType = "String", required = true)
    @GetMapping("/getDeptSelectTree")
    public CommonResult<List<SelectTreeVo>> getDeptSelectTree(@RequestParam String enterpriseId) {
        try {
            List<SelectTreeVo> treeNodeList = Lists.newArrayList();
            Map<String, Object> params = Maps.newHashMap();
            if (StringUtils.isNotBlank(enterpriseId)) {
                params.put("enterpriseId", enterpriseId);
            }
            List<EnterpriseDepartment> departmentList = enterpriseDepartmentService.selectEnterpriseDepartmentList(params);
            if (!departmentList.isEmpty()) {
                departmentList.forEach(dept -> {
                    SelectTreeVo selectTreeVo = new SelectTreeVo();
                    selectTreeVo.setId(dept.getId().toString());
                    selectTreeVo.setName(dept.getDepartmentName());
                    selectTreeVo.setParentId(dept.getParentId().toString());
                    treeNodeList.add(selectTreeVo);
                });
            }
            return CommonResult.success(treeNodeList);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("运行异常，请联系管理员");
        }
    }

}
