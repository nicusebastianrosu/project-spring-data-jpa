package com.sdacademy.projectwithpersistence.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.sdacademy.projectwithpersistence.persistence.repository")
@EntityScan("com.sdacademy.projectwithpersistence.persistence.model")
public class AppConfig {

}
