package com.dearnewyear.dny.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Value("${swagger.base-package}")
    private String BASE_PACKAGE;

    @Value("${swagger.path}")
    private String API_PATH;

    @Value("${swagger.title}")
    private String API_TITLE;

    @Value("${swagger.description}")
    private String API_DESCRIPTION;

    @Value("${swagger.version}")
    private String API_VERSION;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.ant(API_PATH))
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(API_TITLE)
                .description(API_DESCRIPTION)
                .version(API_VERSION)
                .build();
    }
}
