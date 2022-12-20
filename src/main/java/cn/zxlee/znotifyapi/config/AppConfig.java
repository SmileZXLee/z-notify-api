package cn.zxlee.znotifyapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.util.function.Predicate;

/**
 * @program: z-notify-api
 * @description: 应用配置类
 * @author: zxlee
 * @create: 2022-12-20 19:05
 **/
@Configuration
public class AppConfig {
    /**
     * 全局请求日志配置
     * @return
     */
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeHeaders(true);
        Predicate<String> p = str -> "token".equals(str);
        loggingFilter.setHeaderPredicate(p);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(2000);
        return loggingFilter;
    }
}
