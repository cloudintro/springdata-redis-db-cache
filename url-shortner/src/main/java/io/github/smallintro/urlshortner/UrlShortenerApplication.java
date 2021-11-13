package io.github.smallintro.urlshortner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class UrlShortenerApplication {
    private Logger logger = LoggerFactory.getLogger(UrlShortenerApplication.class);

    @Value("${server.port:8080}")
    private String serverPort;

    public static void main(String[] args) {
        SpringApplication.run(UrlShortenerApplication.class, args);
    }

    @PostConstruct
    public void afterInit() {
        logger.info("access service at http://localhost:{}/swagger-ui/index.html", serverPort);
    }
}
