package com.raptor.service.impl;

import com.raptor.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.cloud.stream.messaging.Source;
import java.util.UUID;

/**
 * @author raptor
 * @description MessageProviderImpl
 * @date 2021/9/2 20:06
 */
//定义消息的推送管道
@EnableBinding(Source.class)
public class MessageProviderImpl implements IMessageProvider {
    // 消息发送管道

    private MessageChannel output;

    @Autowired
    public MessageProviderImpl(MessageChannel output) {
        this.output = output;
    }

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("*****serial: " + serial);
        return null;
    }
}

