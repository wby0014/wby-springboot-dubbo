package com.wby.common.core.swagger2;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.collect.Lists;
import com.wby.common.core.constant.CommonConstant;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description Swagger2配置
 * @Author JacksonTu
 * @Date 2019/11/1 11:57
 */
@Slf4j
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
@ConditionalOnProperty(prefix = "hdw.swagger2", name = "enabled", havingValue = "true")
public class Swagger2Config {

    @Resource
    private Swagger2Properties swagger2Properties;

    public Swagger2Config() {
        log.debug("swagger2 [{}]", swagger2Properties);
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(setHeaderToken());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 文档标题
                .title(swagger2Properties.getTitle())
                // 文档描述
                .description(swagger2Properties.getDescription())
                .version(swagger2Properties.getVersion())
                .license("MIT 协议")
                .licenseUrl("http://www.opensource.org/licenses/MIT")
                .build();
    }

    private List<RequestParameter> setHeaderToken() {
        List<RequestParameter> parameters = Lists.newArrayList();
        parameters.add(new RequestParameterBuilder()
                .in(ParameterType.HEADER)
                .name(CommonConstant.JWT_DEFAULT_TOKEN_NAME)
                .description("token令牌")
                .required(false)
                .build());

        return parameters;
    }

}
