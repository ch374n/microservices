package com.nimbalkar.currencyconversionservice.controller;

import com.nimbalkar.currencyconversionservice.entity.CurrencyConversion;
import com.nimbalkar.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {
    @Autowired
    private CurrencyExchangeProxy proxy;
    
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ) {

        ResponseEntity<CurrencyConversion> response = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class, new HashMap<>() {{
                    put("from", from);
                    put("to", to);
                }});
        CurrencyConversion body = response.getBody();
        return new CurrencyConversion(body.getId(), from, to, quantity, body.getConversionMultiple(), quantity.multiply(body.getConversionMultiple()), body.getPort());
    }
    
    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ) {
        CurrencyConversion body = proxy.retrieveExchangeValue(from, to);
        return new CurrencyConversion(body.getId(), from, to, quantity, body.getConversionMultiple(), quantity.multiply(body.getConversionMultiple()), body.getPort());
    } 
}
