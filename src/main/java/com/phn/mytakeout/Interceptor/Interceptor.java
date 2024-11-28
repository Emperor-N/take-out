package com.phn.mytakeout.Interceptor;


import com.phn.mytakeout.properties.JwtProperties;
import com.phn.mytakeout.utils.JwtTool;
import com.phn.mytakeout.utils.ThreadLocalUserContext;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class Interceptor implements HandlerInterceptor {

    private final JwtProperties jwtProperties;

    private final JwtTool jwtTool;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取请求头中的 token
        String[] cookies1 = request.getHeader("Cookie").split(";");
        String token = null;
        for (String cookie : cookies1) {
            if(cookie.contains("token")){
                token = cookie.split("=")[1];
            }
        }

        // 2.校验token
        Claims claims = jwtTool.parseToken(jwtProperties.getSecret(), token);
        int userId = (int)claims.get("userId");
        // 3.存入上下文
        ThreadLocalUserContext.setUser((long) userId);
        // 4.放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
