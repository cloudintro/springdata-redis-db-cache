package io.github.smallintro.redis.service;

public interface MessagePublisher {
    void publishMessage(final String message);
}
