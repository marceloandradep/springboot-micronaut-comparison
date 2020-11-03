package com.pereira.httpservicesb.services;

import com.pereira.httpservicesb.domain.Webhook;
import com.pereira.httpservicesb.producers.WebhookProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final WebhookProducer webhookProducer;

    public void sendWebhook(Webhook webhook) {
        webhookProducer.sendWebhook(webhook);
    }
}
