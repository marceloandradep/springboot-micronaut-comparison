package com.pereira.controllers;

import com.pereira.domain.Message;
import com.pereira.services.MessageService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.reactivex.Single;

import javax.inject.Inject;

@Controller("/messages")
public class MessageController {

    @Inject
    private MessageService messageService;

    @Post(consumes = MediaType.APPLICATION_JSON)
    public Single<Message> sendMessage(@Body Message message) {
        return messageService.sendMessage(message, "message/send");
    }

}
