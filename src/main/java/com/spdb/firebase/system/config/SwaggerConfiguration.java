package com.spdb.firebase.system.config;

import com.spdb.firebase.system.SpringProfile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Profile(SpringProfile.SWAGGER)
@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    @Bean
    Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api")
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }

}
