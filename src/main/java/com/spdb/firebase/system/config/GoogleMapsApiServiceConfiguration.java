package com.spdb.firebase.system.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("google-maps-api")
@Data
public class GoogleMapsApiServiceConfiguration {
    private String apiKey;
}
