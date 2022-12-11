package cn.zxlee.znotifyapi.interceptor;

import cn.zxlee.znotifyapi.annotation.NoLoginAuth;
import cn.zxlee.znotifyapi.exception.LoginAuthErrorException;
import cn.zxlee.znotifyapi.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: z-notify-api
 * @description:
 * @author: zxlee
 * @create: 2022-11-26 14:58
 **/

@Slf4j
@Component
public class UserHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果添加了NoLoginAuth注解，则不进行登录token校验
        if (!(handler instanceof HandlerMethod) || ((HandlerMethod) handler).getMethodAnnotation(NoLoginAuth.class) != null) {
            return true;
        }

        log.info("UserHandlerInterceptor拦截器开始执行" + request.getRequestURI());
        // 获取请求头中的token
        String token = request.getHeader("token");
        if (StringUtils.hasText(tokenUtils.verifyToken(token))) {
            return true;
        }
        // 抛出token校验失败异常
        response.setStatus(401);
        response.getWriter().append("unauthorized error");
        return false;
    }
}
