package com.saga.example.axon;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * https://blog.csdn.net/qq_40663787/article/details/106333663
 *
 * https://www.cnblogs.com/xiaoruirui/p/11809795.html
 *
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket docket1(Environment environment) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("HybridGaw1")
                .enable(true)
                .select()
                // 指定要扫描的包
                .apis(RequestHandlerSelectors.any())
                .build();
    }

    @Bean
    public Docket docket(Environment environment) {

        // 设置了swagger的docket的环境
//        boolean flag = environment.acceptsProfiles(Profiles.of("dev", "test"));

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("HybridGaw")
                .enable(true)
                .select()
                // 指定要扫描的包
                .apis(RequestHandlerSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Hybrid", "www.baidu.com", "1831938181@qq.com");

        return new ApiInfo("Api文档", "Api文档",
                "1.0", "www.baidu.com",
                contact, "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());
    }
}



