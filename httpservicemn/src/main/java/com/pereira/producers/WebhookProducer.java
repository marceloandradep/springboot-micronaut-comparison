package com.pereira.producers;

import com.pereira.domain.Webhook;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.reactivex.Completable;

@KafkaClient
public interface WebhookProducer {

    @Topic("webhooks")
    Completable sendWebhook(Webhook webhook);

}
