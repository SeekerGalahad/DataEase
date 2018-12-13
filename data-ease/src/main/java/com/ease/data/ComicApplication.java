package com.ease.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ImportResource(locations = {"classpath:druid-bean.xml"})
@EnableAsync
@EnableJpaRepositories
@EnableCaching
public class ComicApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComicApplication.class, args);
    }

}
