package com.pereira.producer;

import com.pereira.domain.Message;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface MessageProducer {

    @Topic("messages")
    void sendMessage(Message message);

}
