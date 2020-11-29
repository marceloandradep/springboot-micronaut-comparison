package com.pereira.services;

import com.pereira.domain.Webhook;
import com.pereira.producers.WebhookProducer;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DeliveryService {

    @Inject
    private WebhookProducer webhookProducer;

    public void sendWebhook(Webhook webhook) {
        webhookProducer.sendWebhook(webhook);
    }

}
