package com.spdb.firebase.system.config;

import com.querydsl.jpa.HQLTemplates;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class JpaConfiguration {

    @Bean
    public JPQLQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(HQLTemplates.DEFAULT, entityManager);
    }

}
