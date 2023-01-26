package com.ssafy.logit.config;

import com.ssafy.logit.model.user.repository.UserRepo;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
//@EnableJpaRepositories(basePackageClasses = {UserRepo.class})
public class RootContextConfig {
}
