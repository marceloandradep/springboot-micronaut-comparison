package com.pereira.messagingservicesb.services;

import com.pereira.messagingservicesb.domain.Message;
import com.pereira.messagingservicesb.domain.Subscription;
import com.pereira.messagingservicesb.domain.Webhook;
import com.pereira.messagingservicesb.repositories.SubscriptionRepository;
import com.pereira.messagingservicesb.repositories.WebhookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final SubscriptionRepository subscriptionRepository;
    private final WebhookRepository webhookRepository;
    private final DeliveryService deliveryService;

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
