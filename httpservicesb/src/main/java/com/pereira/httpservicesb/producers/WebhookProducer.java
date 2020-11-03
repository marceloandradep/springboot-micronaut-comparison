package com.pereira.httpservicesb.producers;

import com.pereira.httpservicesb.domain.Webhook;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebhookProducer {

    private final KafkaTemplate<String, Webhook> kafkaTemplate;

    public void sendWebhook(Webhook webhook) {
        kafkaTemplate.send("webhooks", webhook);
    }
}
