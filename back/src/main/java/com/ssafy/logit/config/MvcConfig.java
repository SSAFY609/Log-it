package com.ssafy.logit.config;

import com.ssafy.logit.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig  implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**") // 기본 적용 경로
                .excludePathPatterns("/user/login/**","/swagger-ui/**","/api-docs/**"); // 적용 제외 경로
    }
}