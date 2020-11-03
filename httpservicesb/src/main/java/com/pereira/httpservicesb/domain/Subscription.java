package com.pereira.httpservicesb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Subscription {

    private String method;
    private String requestUrl;

}
