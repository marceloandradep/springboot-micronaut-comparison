package com.pereira.services;

import com.pereira.domain.Webhook;
import com.pereira.producers.WebhookProducer;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DeliveryService {

    @Inject
    private WebhookProducer webhookProducer;

    public Single<Webhook> sendWebhook(Webhook webhook) {
        return webhookProducer
                .sendWebhook(webhook)
                .toSingleDefault(webhook);
    }

}
