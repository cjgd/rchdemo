package com.github.cjgd.RchMain;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.github.cjgd.repo")
@EntityScan("com.github.cjgd.model.entity")
public class Config {
}
