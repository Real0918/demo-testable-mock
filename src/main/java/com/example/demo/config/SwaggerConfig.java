package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author: ww
 * @date: 2021/4/22 17:18:05
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private Boolean enable;

    private final String BASE_PACKAGE = "com.example.demo";
    private final String MODULE_NAME = "demo";

    @Bean
    public Docket createRestApi() {
        List<Parameter> parameters = new ArrayList<>();
        Parameter aParameter = new ParameterBuilder()
                .name("accessToken")
                .defaultValue("xxxxxxfbaee74a379a694ce68396bc6a")
                .description("accessToken")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        Parameter bParameter = new ParameterBuilder()
                .name("accountId")
                .defaultValue("21d69a98a397458498cd3a9856a4c013")
                .description("accountId")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        parameters.add(aParameter);
        parameters.add(bParameter);
        // 接口是否有默认参数
        parameters.clear();

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalOperationParameters(parameters)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(MODULE_NAME + " Docs")
                .description(MODULE_NAME + " Api文档")
                .contact(MODULE_NAME + " Developers")
                .version("1.0")
                .build();
    }

}
