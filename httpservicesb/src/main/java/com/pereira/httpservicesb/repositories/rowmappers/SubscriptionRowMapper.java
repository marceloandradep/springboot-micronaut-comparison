package com.pereira.httpservicesb.repositories.rowmappers;

import com.pereira.httpservicesb.domain.Subscription;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SubscriptionRowMapper implements RowMapper<Subscription> {
    @Override
    public Subscription mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Subscription(
                resultSet.getString("method"),
                resultSet.getString("request_url")
        );
    }
}
