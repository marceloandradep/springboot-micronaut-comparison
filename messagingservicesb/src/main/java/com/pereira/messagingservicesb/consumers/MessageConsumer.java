package com.pereira.messagingservicesb.consumers;

import com.pereira.messagingservicesb.domain.Message;
import com.pereira.messagingservicesb.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageConsumer {

    private final MessageService messageService;

    @KafkaListener(topics = "messages")
    public void receive(Message message) {
        messageService.sendMessage(message, "message/send");
    }

}
