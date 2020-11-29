package com.pereira.producers;

import com.pereira.domain.Webhook;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface WebhookProducer {

    @Topic("webhooks")
    void sendWebhook(Webhook webhook);

}
