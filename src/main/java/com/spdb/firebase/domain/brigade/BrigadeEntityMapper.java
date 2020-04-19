package com.spdb.firebase.domain.brigade;

import org.springframework.stereotype.Component;

@Component
public class BrigadeEntityMapper {

    public Brigade toBrigade(BrigadeEntity brigadeEntity) {
        return Brigade.builder()
                .id(brigadeEntity.getId())
                .name(brigadeEntity.getName())
                .city(brigadeEntity.getCity())
                .postalCode(brigadeEntity.getPostalCode())
                .street(brigadeEntity.getStreet())
                .squadMaxAmount(brigadeEntity.getSquadMaxAmount())
                .build();
    }

    public BrigadeEntity toBrigadeEntity(Brigade brigade) {
        return BrigadeEntity.builder()
                .id(brigade.getId())
                .name(brigade.getName())
                .city(brigade.getCity())
                .postalCode(brigade.getPostalCode())
                .street(brigade.getStreet())
                .squadMaxAmount(brigade.getSquadMaxAmount())
                .build();
    }
}
