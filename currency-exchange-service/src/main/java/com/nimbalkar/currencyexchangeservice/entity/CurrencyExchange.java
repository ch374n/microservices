package com.nimbalkar.currencyexchangeservice.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CurrencyExchange {
    @Id
    Long id;
    @Column(name = "currency_from")
    String from; 
    @Column(name = "currency_to")
    String to;
    BigDecimal conversionMultiple;
    String port;
}
