package com.pereira.httpservicesb.repositories;

import com.pereira.httpservicesb.domain.Webhook;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
@RequiredArgsConstructor
public class WebhookRepository {

    private static final String INSERT =
            "INSERT INTO webhooks (response_code, response, payload, method, request_url, sent) VALUES (?, ?, ?, ?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;

    public void save(Webhook webhook) {
        jdbcTemplate.update(INSERT, webhook.getResponseCode(), webhook.getResponse(), webhook.getPayload(),
                webhook.getMethod(), webhook.getUrl(), Instant.now());
    }

}
