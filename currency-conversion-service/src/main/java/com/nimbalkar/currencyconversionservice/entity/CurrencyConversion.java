package com.nimbalkar.currencyconversionservice.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CurrencyConversion {
    Long id;
    String from;
    String to;
    BigDecimal quantity;
    BigDecimal conversionMultiple;
    BigDecimal totalCalculateAmount;
    String port;
}
