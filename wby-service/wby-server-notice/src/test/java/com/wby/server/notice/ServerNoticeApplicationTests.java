package com.wby.server.notice;

import com.wby.api.base.enterprise.entity.Enterprise;
import com.wby.api.base.enterprise.service.IEnterpriseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description
 * @Author wby
 * @Date 2020/4/8 17:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ServerNoticeApplicationTests {

    @DubboReference
    private IEnterpriseService enterpriseService;

    @Test
    public void testEnterprise(){
        List<Enterprise> list=enterpriseService.list();
        if(list!=null && list.size()>0){
            list.forEach(enterprise -> {
                System.out.println(enterprise.toString());
            });
        }
    }





}
