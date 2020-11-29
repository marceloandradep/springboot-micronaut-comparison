package com.pereira.services;

import com.pereira.domain.Message;
import com.pereira.domain.Subscription;
import com.pereira.domain.Webhook;
import com.pereira.repositories.SubscriptionRepository;
import com.pereira.repositories.WebhookRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class MessageService {

    @Inject
    private SubscriptionRepository subscriptionRepository;

    @Inject
    private WebhookRepository webhookRepository;

    @Inject
    private DeliveryService deliveryService;

    public Message sendMessage(Message message, String event) {

        List<Subscription> subscriptions = subscriptionRepository.findAll(message, event);
        subscriptions.forEach(subscription -> deliverWebhook(message, subscription));
        return message;
    }

    private void deliverWebhook(Message message, Subscription subscription) {

        Webhook webhook = new Webhook(
                message.getBody(),
                subscription.getMethod(),
                subscription.getRequestUrl());

        deliveryService.sendWebhook(webhook);
        webhookRepository.save(webhook);
    }

}
