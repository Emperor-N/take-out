package com.phn.mytakeout.config;

import com.phn.mytakeout.Interceptor.Interceptor;
import com.phn.mytakeout.properties.AuthProperties;
import com.phn.mytakeout.properties.JwtProperties;
import com.phn.mytakeout.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfiguration implements WebMvcConfigurer {

    private final JwtTool jwtTool;

    private final JwtProperties jwtProperties;

    private final AuthProperties authProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        Interceptor interceptor = new Interceptor(jwtProperties,jwtTool);
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(interceptor);
        //配置拦截路径
        List<String > includePaths = authProperties.getIncludePaths();
        for(String path:includePaths){
            interceptorRegistration.addPathPatterns(path);
        }
        //配置放行路径
        List<String > excludePaths = authProperties.getExcludePaths();
        for(String path:excludePaths){
            interceptorRegistration.excludePathPatterns(path);
        }

    }
}
