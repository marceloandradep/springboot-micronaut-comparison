package com.pereira.services;

import com.pereira.domain.Message;
import com.pereira.domain.Webhook;
import com.pereira.repositories.SubscriptionRepository;
import com.pereira.repositories.WebhookRepository;
import io.reactivex.Observable;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MessageService {

    @Inject
    private SubscriptionRepository subscriptionRepository;

    @Inject
    private WebhookRepository webhookRepository;

    @Inject
    private DeliveryService deliveryService;

    public Single<Message> sendMessage(Message message, String event) {
        return subscriptionRepository
                .findAll(message, event)
                .flatMapObservable(Observable::fromIterable)

                .map(subscription ->
                        new Webhook(
                                message.getBody(),
                                subscription.getMethod(),
                                subscription.getRequestUrl()))

                .flatMapSingle(deliveryService::sendWebhook)
                .flatMapCompletable(webhookRepository::save)
                .toSingleDefault(message);
    }

}
