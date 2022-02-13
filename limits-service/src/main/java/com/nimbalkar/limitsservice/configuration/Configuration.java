package com.nimbalkar.limitsservice.configuration;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "limits-service")
@Component
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Configuration {
    int maximum;
    int minimum;
}
