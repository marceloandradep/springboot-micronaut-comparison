package com.pereira.repositories;

import com.pereira.domain.Message;
import com.pereira.domain.Subscription;
import io.reactivex.Single;
import io.vertx.reactivex.mysqlclient.MySQLPool;
import io.vertx.reactivex.sqlclient.Row;
import io.vertx.reactivex.sqlclient.RowSet;
import io.vertx.reactivex.sqlclient.Tuple;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class SubscriptionRepository {

    private static final String FIND_ALL =
                    "SELECT method, request_url " +
                    "FROM subscriptions " +
                    "WHERE subscriber_id = ? AND event = ?";

    @Inject
    private MySQLPool client;

    public Single<List<Subscription>> findAll(Message message, String event) {
        return client
                .preparedQuery(FIND_ALL)
                .rxExecute(Tuple.of(message.getSubscriberId(), event))
                .map(this::toSubscriptionList);
    }

    private List<Subscription> toSubscriptionList(RowSet<Row> rows) {
        List<Subscription> subscriptions = new ArrayList<>();

        rows.iterator()
                .forEachRemaining(row -> {
                    subscriptions.add(toSubscription(row));
                });

        return subscriptions;
    }

    private Subscription toSubscription(Row row) {
        return new Subscription(row.getString("method"), row.getString("request_url"));
    }

}
