package com.pereira.messagingservicesb.repositories;

import com.pereira.messagingservicesb.domain.Message;
import com.pereira.messagingservicesb.domain.Subscription;
import com.pereira.messagingservicesb.repositories.rowmappers.SubscriptionRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SubscriptionRepository {

    private static final String FIND_ALL =
                    "SELECT method, request_url " +
                    "FROM subscriptions " +
                    "WHERE subscriber_id = ? AND event = ?";

    private final JdbcTemplate jdbcTemplate;
    private final SubscriptionRowMapper rowMapper;

    public List<Subscription> findAll(Message message, String event) {
        return jdbcTemplate.query(FIND_ALL, rowMapper, message.getSubscriberId(), event);
    }
}
