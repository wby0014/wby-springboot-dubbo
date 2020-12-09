package com.wby.server.base;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author wby
 * @description Application
 * @date 2017年9月5日下午8:55:08
 */
@SpringBootApplication
@MapperScan("com.wby.server.base.*.mapper")
public class ServerBaseApplication extends SpringBootServletInitializer {

    protected final static Logger logger = LoggerFactory.getLogger(ServerBaseApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ServerBaseApplication.class, args);
        logger.info("----ServerBaseApplication start----");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        logger.info("----ServerBaseApplication start----");
        return application.sources(ServerBaseApplication.class);
    }

}
