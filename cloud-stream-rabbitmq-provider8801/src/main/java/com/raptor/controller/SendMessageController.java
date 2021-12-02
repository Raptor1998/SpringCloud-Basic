package com.raptor.controller;

import com.raptor.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author raptor
 * @description SendMessageController
 * @date 2021/9/2 20:07
 */
@RestController
public class SendMessageController {
    private IMessageProvider messageProvider;

    @Autowired
    public SendMessageController(IMessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    @GetMapping(value = "/sendMessage")
    public String sendMessage() {
        return messageProvider.send();
    }

}
