package com.pereira.services;

import com.pereira.domain.Webhook;
import com.pereira.producers.WebhookProducer;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;

import static io.reactivex.Single.just;

@Singleton
public class DeliveryService {

    @Inject
    private WebhookProducer webhookProducer;

    public Single<Webhook> sendWebhook(Webhook webhook) {
        webhookProducer.sendWebhook(webhook);
        return just(webhook);
    }

}
