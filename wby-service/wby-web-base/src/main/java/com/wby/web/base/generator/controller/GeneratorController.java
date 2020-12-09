package com.wby.web.base.generator.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.wby.api.base.generator.dto.TableDto;
import com.wby.api.base.generator.service.IGeneratorService;
import com.wby.common.core.api.CommonResult;
import org.apache.commons.io.IOUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author wby
 * @version 1.0
 * @description
 * @since 2020/11/22 11:43
 */
@RestController
@RequestMapping("generator")
public class GeneratorController {

    @DubboReference
    private IGeneratorService generatorService;

    /**
     * 分页
     *
     * @param tableDto
     * @return
     */
    @GetMapping("/list")
    public CommonResult pageList(TableDto tableDto) {
        return CommonResult.success(generatorService.pageList(tableDto));
    }

    /**
     * 生成代码
     */
    @GetMapping("/code")
    public void code(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] tableNames = new String[]{};
        String tables = request.getParameter("tableNames");
        if (tables.indexOf(",") > 0) {
            tableNames = tables.split(",");
        } else {
            tableNames = new String[]{tables};
        }
        byte[] data = generatorService.generatorCode(tableNames);
        String fileName = "wby_" + DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN) + ".zip";
        response.reset();
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + java.net.URLEncoder.encode(fileName, "utf-8"));
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType(MediaType.MULTIPART_FORM_DATA_VALUE);
        // 设置强制下载不打开
        response.setContentType("application/force-download");
        response.setCharacterEncoding("utf-8");
        IOUtils.write(data, response.getOutputStream());
    }
}
