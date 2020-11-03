package com.pereira.repositories;

import com.pereira.domain.Webhook;
import io.reactivex.Completable;
import io.vertx.reactivex.mysqlclient.MySQLPool;
import io.vertx.reactivex.sqlclient.Tuple;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;

@Singleton
public class WebhookRepository {

    private static final String INSERT =
            "INSERT INTO webhooks (response_code, response, payload, method, request_url, sent) VALUES (?, ?, ?, ?, ?, ?)";

    @Inject
    private MySQLPool client;

    public Completable save(Webhook webhook) {
        return client
                .preparedQuery(INSERT)
                .rxExecute(
                        Tuple.of(
                                webhook.getResponseCode(), webhook.getResponse(), webhook.getPayload(),
                                webhook.getMethod(), webhook.getUrl(), Instant.now()
                        )
                ).ignoreElement();
    }

}
