package com.crossent.monitoring.portal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.crossent.monitoring.portal.jpa.repository"})
@EnableCaching
//@EnableElasticsearchRepositories("com.crossent.monitoring.portal.elasticsearch.repository")
public class MonitoringApplication {

    private static Logger logger  = LoggerFactory.getLogger(MonitoringApplication.class);

    public static void main(String[] args) {

        System.out.println("start application.");
        SpringApplication.run(MonitoringApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            logger.info("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                logger.debug(beanName);
            }

        };
    }

}
