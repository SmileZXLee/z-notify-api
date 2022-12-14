package cn.zxlee.znotifyapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: z-notify-api
 * @description:
 * @author: zxlee
 * @create: 2022-11-25 22:58
 **/
@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.zxlee.znotifyapi"))
                .build()
                .globalRequestParameters(globalRequestParameters());
    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("z-notify-api接口文档")
                .description("")
                .termsOfServiceUrl("https://github.com/SmileZXLee/z-notify-api")
                .contact(new Contact("zxlee","https://github.com/SmileZXLee","admin@zxlee.cn"))
                .version("0.0.1")
                .build();
    }

    //生成全局通用参数
    private List<RequestParameter> globalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
//        parameters.add(new RequestParameterBuilder()
//                .name("token")
//                .description("token")
//                .required(false)
//                .in(ParameterType.HEADER)
//                .build());
        return parameters;
    }
}
