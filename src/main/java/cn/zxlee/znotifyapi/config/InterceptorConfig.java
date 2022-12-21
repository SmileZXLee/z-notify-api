package cn.zxlee.znotifyapi.config;

import cn.zxlee.znotifyapi.interceptor.UserHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @program: z-notify-api
 * @description:
 * @author: zxlee
 * @create: 2022-11-26 15:43
 **/

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private UserHandlerInterceptor userHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器 拦截路径为 /** 放行 /user/to_login /user/login路径
        registry.addInterceptor(userHandlerInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/*/user/login","/swagger-ui/**","/swagger-resources/**","/v3/api-docs","/druid/**");
    }
}
