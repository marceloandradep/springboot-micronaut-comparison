package com.zipwhip.slowservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MockController {

    @Value("${wait-time}")
    private long waitTime;

    @PostMapping("/slowOperation")
    public String slowOperation() throws InterruptedException {
        Thread.sleep(waitTime);
        log.info("Processed");
        return "ok";
    }

}
