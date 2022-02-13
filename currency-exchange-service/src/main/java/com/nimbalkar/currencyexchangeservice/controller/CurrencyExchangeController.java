package com.nimbalkar.currencyexchangeservice.controller;

import com.nimbalkar.currencyexchangeservice.entity.CurrencyExchange;
import com.nimbalkar.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {
    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class); 
    
    @Autowired
    Environment environment; 
    @Autowired
    CurrencyExchangeRepository exchangeRepository;
    
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        logger.info("retrieveExchangeValue called with {} to {} ", from, to);
        CurrencyExchange currencyExchange = exchangeRepository
                .findByFromAndTo(from, to)
                .orElseThrow(() -> new RuntimeException("Unable to find data for " + from + " to " + to));
        
        return new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50), environment.getProperty("local.server.port"));
    }
}


