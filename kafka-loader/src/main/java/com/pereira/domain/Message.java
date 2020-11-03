package com.pereira.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Data
@RequiredArgsConstructor
public class Message {

    private final long subscriberId;
    private final String from;
    private final String to;
    private final String body;
    private final Instant created;

}
