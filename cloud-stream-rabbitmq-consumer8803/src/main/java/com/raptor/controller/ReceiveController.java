package com.raptor.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author raptor
 * @description ReceiveController
 * @date 2021/9/2 20:20
 */
@Component
@EnableBinding(Sink.class)
public class ReceiveController {
    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        System.out.println("消费之2号，port：" + serverPort + ",messageBody:" + message.getPayload());
    }
}
