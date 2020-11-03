package com.pereira.messagingservicesb.services;

import com.pereira.messagingservicesb.domain.Webhook;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final RestTemplate restTemplate;

    public void sendWebhook(Webhook webhook) {
        HttpEntity<String> httpEntity = new HttpEntity<>(webhook.getPayload());

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                webhook.getUrl(),
                webhook.getMethod().equals("POST") ? HttpMethod.POST : HttpMethod.PUT,
                httpEntity, String.class);

        webhook.setResponseCode(responseEntity.getStatusCodeValue());
        webhook.setResponse(responseEntity.getBody());
    }
}
