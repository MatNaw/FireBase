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
                .latitude(brigadeEntity.getLatitude())
                .longitude(brigadeEntity.getLongitude())
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
                .latitude(brigade.getLatitude())
                .longitude(brigade.getLongitude())
                .squadMaxAmount(brigade.getSquadMaxAmount())
                .build();
    }
}
