package com.spdb.firebase.system.config;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;

public class YmlPropertySourceFactory implements PropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String s, EncodedResource resource) throws IOException {
        String resourceName = resource.getResource().getDescription();
        return new YamlPropertySourceLoader().load(resourceName, resource.getResource()).get(0);
    }
}
