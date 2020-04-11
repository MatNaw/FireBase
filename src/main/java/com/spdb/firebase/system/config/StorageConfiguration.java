package com.spdb.firebase.system.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("storage")
@Data
public class StorageConfiguration {
    private String importDirectory;
}
