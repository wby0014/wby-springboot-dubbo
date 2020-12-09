package com.wby.server.base.generator.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wby.api.base.generator.dto.TableDto;
import com.wby.api.base.generator.entity.TableEntity;
import com.wby.api.base.generator.service.IGeneratorService;
import com.wby.common.core.vo.PageVo;
import com.wby.server.base.generator.mapper.GeneratorMapper;
import com.wby.server.base.generator.utils.GeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * @author wby
 * @version 1.0
 * @description
 * @since 2020/11/22 11:40
 */
@DubboService
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GeneratorServiceImpl extends ServiceImpl<GeneratorMapper, TableEntity> implements IGeneratorService {
    @Override
    public PageVo<Map<String, Object>> pageList(TableDto tableDto) {
        Page page = new Page();
        // 设置当前页码
        page.setCurrent(tableDto.getPage());
        // 设置页大小
        page.setSize(tableDto.getLimit());

        return new PageVo<>(this.baseMapper.pageList(page, tableDto));
    }

    @Override
    public Map<String, String> findByTableName(String tableName) {
        return this.baseMapper.findByTableName(tableName);
    }

    @Override
    public List<Map<String, String>> listByTableName(String tableName) {
        return this.baseMapper.listByTableName(tableName);
    }

    @Override
    public byte[] generatorCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            //查询表信息
            Map<String, String> table = findByTableName(tableName);
            //查询列信息
            List<Map<String, String>> columns = listByTableName(tableName);
            //生成代码
            GeneratorUtil.generatorCode(table, columns, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}
