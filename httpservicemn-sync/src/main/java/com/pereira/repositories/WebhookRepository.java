package com.pereira.repositories;

import com.pereira.domain.Webhook;
import io.micronaut.data.jdbc.runtime.JdbcOperations;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.sql.Date;

@Singleton
public class WebhookRepository {

    private static final String INSERT =
            "INSERT INTO webhooks (response_code, response, payload, method, request_url, sent) VALUES (?, ?, ?, ?, ?, ?)";

    @Inject
    private JdbcOperations jdbcOperations;

    @Transactional
    public void save(Webhook webhook) {
        jdbcOperations.prepareStatement(INSERT, statement -> {

            statement.setInt(1, webhook.getResponseCode());
            statement.setString(2, webhook.getResponse());
            statement.setString(3, webhook.getPayload());
            statement.setString(4, webhook.getMethod());
            statement.setString(5, webhook.getUrl());
            statement.setDate(6, new Date(System.currentTimeMillis()));

            return statement.execute();
        });
    }

}
