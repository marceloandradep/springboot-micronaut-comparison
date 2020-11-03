package com.pereira.services;

import com.pereira.domain.Webhook;
import io.micronaut.core.io.buffer.ByteBuffer;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.uri.UriBuilder;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.nio.charset.Charset;

@Singleton
public class DeliveryService {

    @Inject
    private RxHttpClient httpClient;

    public Single<Webhook> sendWebhook(Webhook webhook) {
        String uri = UriBuilder.of(webhook.getUrl()).toString();

        HttpRequest<String> httpRequest;
        if (webhook.getMethod().equals("POST")) {
            httpRequest = HttpRequest.POST(uri, webhook.getPayload());
        } else {
            httpRequest = HttpRequest.PUT(uri, webhook.getPayload());
        }

        return httpClient
                .exchange(httpRequest)
                .map(byteBufferHttpResponse -> setResponse(webhook, byteBufferHttpResponse))
                .singleOrError();
    }

    private Webhook setResponse(Webhook webhook, HttpResponse<ByteBuffer> byteBufferHttpResponse) {

        webhook.setResponseCode(
                byteBufferHttpResponse.getStatus().getCode()
        );

        byteBufferHttpResponse
                .getBody()
                .ifPresent(
                        byteBuffer -> webhook.setResponse(byteBuffer.toString(Charset.defaultCharset()))
                );

        return webhook;
    }

}
