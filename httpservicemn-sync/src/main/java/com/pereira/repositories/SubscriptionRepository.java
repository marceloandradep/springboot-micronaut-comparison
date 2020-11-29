package com.pereira.repositories;

import com.pereira.domain.Message;
import com.pereira.domain.Subscription;
import io.micronaut.data.jdbc.runtime.JdbcOperations;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class SubscriptionRepository {

    private static final String FIND_ALL =
                    "SELECT method, request_url " +
                    "FROM subscriptions " +
                    "WHERE subscriber_id = ? AND event = ?";

    @Inject
    private JdbcOperations jdbcOperations;

    @Transactional
    public List<Subscription> findAll(Message message, String event) {
        return jdbcOperations.prepareStatement(FIND_ALL, statement -> {

            statement.setLong(1, message.getSubscriberId());
            statement.setString(2, event);

            ResultSet resultSet = statement.executeQuery();
            return jdbcOperations.entityStream(resultSet, Subscription.class).collect(Collectors.toList());
        });
    }

}
