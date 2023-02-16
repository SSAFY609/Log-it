package com.ssafy.logit;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableEncryptableProperties
public class LogitApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogitApplication.class, args);
	}
}
