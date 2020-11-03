package com.pereira.httpservicesb.controllers;

import com.pereira.httpservicesb.domain.Message;
import com.pereira.httpservicesb.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/messages")
    @ResponseBody
    public Message sendMessage(@RequestBody Message message) {
        return messageService.sendMessage(message, "message/send");
    }

}
