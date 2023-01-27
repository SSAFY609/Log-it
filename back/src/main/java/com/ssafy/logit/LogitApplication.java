package com.ssafy.logit;

import com.ssafy.logit.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaAuditing
@SpringBootApplication
public class LogitApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(LogitApplication.class, args);
	}

	@Autowired
	private JwtInterceptor jwtInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/**") // 기본 적용 경로
				.excludePathPatterns("/user/login/**").excludePathPatterns("/user/regist/**"); // 적용 제외 경로
	}
}
