package com.pereira.httpservicesb.domain;

import lombok.Data;

import java.time.Instant;

@Data
public class Message {

    private long subscriberId;
    private String from;
    private String to;
    private String body;
    private Instant created;

}
