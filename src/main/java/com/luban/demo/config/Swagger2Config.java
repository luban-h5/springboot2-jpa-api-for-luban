package com.luban.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Yang Hao
 * @date 2019/11/17 20:58
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket petApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.luban.demo.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    /**
     * 构建api文档的详细信息函数
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("API 描述")
                //创建人
                .contact(new Contact("spring-boot-api-for-editor", "https://github.com/luban-h5/spring-boot-api-for-editor", ""))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }


}
