package com.spdb.firebase.system.config;

import com.fasterxml.jackson.databind.Module;
import io.vavr.jackson.datatype.VavrModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class JacksonModulesConfiguration {

    @Bean
    Module vavrModule() {
        return new VavrModule();
    }

}
