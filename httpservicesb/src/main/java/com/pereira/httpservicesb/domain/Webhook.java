package com.pereira.httpservicesb.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Data
@RequiredArgsConstructor
public class Webhook {

    private final String payload;
    private final String method;
    private final String url;
    private int responseCode;
    private String response;
    private Instant sent;

}
