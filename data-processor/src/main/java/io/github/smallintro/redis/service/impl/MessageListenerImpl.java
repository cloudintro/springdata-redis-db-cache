package io.github.smallintro.redis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MessageListenerImpl implements MessageListener {

    public static List<String> messageList = new ArrayList<>();

    public void onMessage(Message message, final byte[] pattern) {
        String msgText = new String(message.getBody());
        messageList.add(msgText);
        log.info("Message received: " + msgText);
    }
}
