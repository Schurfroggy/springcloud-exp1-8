package com.shop.myshopuser;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerConfig {
    @Bean
    public Docket buildDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
               .apiInfo(buildApiInf())
               .select()
               .apis(RequestHandlerSelectors.basePackage(""))
               .paths(PathSelectors.any())
               .build();
    }
    private ApiInfo buildApiInf(){
        return new ApiInfoBuilder()
               .title("用户接口详情")
               .description("Zuul+Swagger2构建RESTful APIs")
               .version("1.0")
               .build();
    }
}
