package io.github.smallintro.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DataProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataProcessorApplication.class, args);
    }
}
