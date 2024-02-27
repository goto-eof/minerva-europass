package com.andreidodu.minervaeuropass.configuration;

import com.andreidodu.minervaeuropass.constants.ApplicationConst;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = ApplicationConst.REPOSITORY_PACKAGE)
@EnableTransactionManagement
public class PersistenceConfiguration {
}
