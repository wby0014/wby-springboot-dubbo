package com.wby.web.base;

import com.wby.api.base.enterprise.service.IEnterpriseService;
import com.wby.common.core.constant.CommonConstant;
import com.wby.web.base.interceptor.InterceptorProperties;
import com.wby.web.base.mail.dto.MailDto;
import com.wby.web.base.mail.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Description com.wby.pattern
 * @Author wby
 * @Date 2020/4/8 17:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class WebBaseApplicationTests {
    @DubboReference
    private IEnterpriseService enterpriseService;

    @Resource
    private Environment env;

    @Resource
    private InterceptorProperties interceptorProperties;

    @Resource
    private MailService mailService;

//    /**
//     * redisson分布式锁测试
//     * 遍历 HashMap测试
//     * @throws InterruptedException
//     */
//    @Test
//    public void getList() throws InterruptedException {
//        String redisKey = env.getProperty("spring.application.name") + ":" + IdUtil.fastUUID();
//        RLock lock = redissonClient.getLock(redisKey);
//        //TODO: 加锁以后10秒钟自动解锁
//        //TODO: 无需调用unlock方法手动解锁
//        lock.lock(10, TimeUnit.SECONDS);
//        //TODO: 尝试加锁，最多等待30秒，上锁以后10秒自动解锁
//        //TODO:第一个参数30s=表示尝试获取分布式锁，并且最大的等待获取锁的时间为30s
//        //TODO:第二个参数10s=表示上锁之后，10s内操作完毕将自动释放锁
//        boolean lockFlag = lock.tryLock(30, 10, TimeUnit.SECONDS);
//        if (lockFlag) {
//            try {
//                ConcurrentMap<String, Object> concurrentMap = new ConcurrentHashMap<>();
//                List<Map<String, Object>> mapList = enterpriseService.selectEnterpriseList(concurrentMap);
//                if (!mapList.isEmpty()) {
//                    //TODO: 使用 Lambda 表达式遍历 HashMap
//                    mapList.stream().forEach(map -> {
//                        map.forEach((key, value) -> {
//                            System.out.println(key);
//                            System.out.println(value);
//                        });
//                        //TODO: 使用 Stream API 遍历 HashMap
//                        map.entrySet().forEach((entry) -> {
//                            System.out.println(entry.getKey());
//                            System.out.println(entry.getValue());
//                        });
//                    });
//                }
//            } finally {
//                lock.unlock();
//            }
//        }
//    }

    @Test
    public void getUploadProperties() {
        if (!interceptorProperties.getUpload().getAllowFileExtensions().isEmpty()) {
            interceptorProperties.getUpload().getAllowFileExtensions().stream().forEach(s -> {
                System.out.println(s);
            });
        } else {
            System.out.println("空");
        }
    }

    /**
     * 加密
     *
     * @throws Exception
     */
    @Test
    public void testEncrypt() throws Exception {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        //TODO: 加密的算法，这个算法是默认的
        config.setAlgorithm("PBEWithMD5AndDES");
        //TODO: 加密的密钥
        config.setPassword(CommonConstant.JWT_DEFAULT_ISSUER);
        standardPBEStringEncryptor.setConfig(config);
        //xy_world_way@126.com
        String plainText = "abc123";
        String encryptedText = standardPBEStringEncryptor.encrypt(plainText);
        System.out.println(encryptedText);
    }

    /**
     * 解密
     *
     * @throws Exception
     */
    @Test
    public void testDecrypt() throws Exception {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        //TODO: 加密的算法，这个算法是默认的
        config.setAlgorithm("PBEWithMD5AndDES");
        //TODO: 加密的密钥
        config.setPassword(CommonConstant.JWT_DEFAULT_ISSUER);
        standardPBEStringEncryptor.setConfig(config);
        String encryptedText = "xRL1CVgzfFKBnPxCr+xjkg==";
        String plainText = standardPBEStringEncryptor.decrypt(encryptedText);
        System.out.println(plainText);
    }

    @Test
    public void testSendEmail() {
        MailDto mailDto = new MailDto();
        mailDto.setContent("这是一封测试邮件");
        mailDto.setContent("测试邮件");
        mailDto.setTos(new String[]{"674717739@qq.com"});
        mailService.sendSimpleEmail(mailDto);
    }

}
