package io.github.smallintro.urlshortner.service;

import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class AppService {
    Logger logger = LoggerFactory.getLogger(AppService.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${server.port:8080}")
    private String serverPort;

    public String getShortURL(String longUrl) {
        String urlId = Hashing.murmur3_32().hashString(longUrl, StandardCharsets.UTF_8).toString();
        redisTemplate.opsForValue().setIfAbsent(urlId, longUrl);
        String shortUrl = String.format("http://localhost:%s/%s", serverPort, urlId);
        logger.info("Generated shortUrl is [{}] for longUrl [{}]", shortUrl, longUrl);
        return shortUrl;

    }

    public String getActualUrl(String urlId) {
        String longUrl = redisTemplate.opsForValue().get(urlId);
        logger.info("Actual longUrl is [{}] for urlId [{}]", longUrl, urlId);
        return longUrl;
    }
}
