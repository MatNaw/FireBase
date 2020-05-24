package com.spdb.firebase.domain.brigade;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Brigade {
    private Long id;
    private String name;
    private String city;
    private String postalCode;
    private String street;
    private Double latitude;
    private Double longitude;
    private Long squadMaxAmount;

    public static Brigade fromBrigadeEntity(BrigadeEntity brigadeEntity) {
        return Brigade.builder()
                .id(brigadeEntity.getId())
                .name(brigadeEntity.getName())
                .city(brigadeEntity.getCity())
                .postalCode(brigadeEntity.getPostalCode())
                .street(brigadeEntity.getStreet())
                .squadMaxAmount(brigadeEntity.getSquadMaxAmount())
                .build();
    }

    public static BrigadeEntity toBrigadeEntity(Brigade brigade) {
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
