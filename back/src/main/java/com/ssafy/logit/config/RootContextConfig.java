package com.ssafy.logit.config;

import com.ssafy.logit.model.user.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
// @EnableJpaRepositories(basePackageClasses = {UserRepository.class})
public class RootContextConfig {
}
