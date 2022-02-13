package com.nimbalkar.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class CircuitBreakerController {
    
    @GetMapping("/sample-api")
    //@Retry(name = "sample-name", fallbackMethod = "hardCodedResponse")
    //@CircuitBreaker(name = "default", fallbackMethod = "hardCodedResponse")
    //@RateLimiter(name = "default")
    // only allow 10000 call in 10s
    @Bulkhead(name = "default")
    public String sampleApi() {
        log.info("sample API call received");
        ResponseEntity<String> body = new RestTemplate().getForEntity("http://localhost:8080/some-dummy", String.class);
        return body.getBody();
    }
    
    public String hardCodedResponse(Exception ex) {
        return "fallback-response";
    }
}
