package com.pereira.consumers;

import com.pereira.domain.Message;
import com.pereira.services.MessageService;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;

@Slf4j
@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class MessageConsumer {

    @Inject
    private MessageService messageService;

    @Topic("messages")
    public void receive(Single<Message> message) {
        message
                .flatMap(payload -> messageService.sendMessage(payload, "message/send"))
                .ignoreElement()
                .subscribe();
    }

}
