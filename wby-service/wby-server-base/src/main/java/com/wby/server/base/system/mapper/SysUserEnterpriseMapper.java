package com.wby.server.base.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wby.api.base.system.entity.SysUserEnterprise;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 监管用户与企业关联表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
public interface SysUserEnterpriseMapper extends BaseMapper<SysUserEnterprise> {

    /**
     * 根据监管用户ID查找企业
     *
     * @param userId
     * @return
     */
    @Select("select t.enterprise_id as enterpriseId from t_sys_user_enterprise t LEFT JOIN t_sys_user t2 on t.user_id=t2.id where t2.id=#{userId}")
    @ResultType(String.class)
    List<Long> selectEnterpriseIdByUserId(@Param("userId") Long userId);


    /**
     * 根据监管用户名称查找关联的企业
     *
     * @param loginName
     * @return
     */
    @Select("select t.enterprise_id as enterpriseId from t_sys_user_enterprise t LEFT JOIN t_sys_user t2 on t.user_id=t2.id where t2.login_name=#{loginName}")
    @ResultType(String.class)
    List<String> selectEnterpriseIdByUserName(@Param("loginName") String loginName);

    /**
     * 根据用户批量删除
     *
     * @param userIds
     */
    void deleteBatchByUserIds(Long[] userIds);

    /**
     * 根据企业批量删除
     *
     * @param enterpriseIds
     */
    void deleteBatchByEnterpriseIds(Long[] enterpriseIds);
}
