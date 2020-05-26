package com.spdb.firebase.domain.fire;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
public class Fire {
    private Long id;
    private String city;
    private String postalCode;
    private String street;
    private Double latitude;
    private Double longitude;
    private LocalDate date;
    private Status status;
    private List<Squad> squads;

    public static Fire fromFireEntity(FireEntity fireEntity) {
        return Fire.builder()
                .id(fireEntity.getId())
                .date(fireEntity.getDate())
                .postalCode(fireEntity.getPostalCode())
                .city(fireEntity.getCity())
                .street(fireEntity.getStreet())
                .latitude(fireEntity.getLatitude())
                .longitude(fireEntity.getLongitude())
                .status(fireEntity.getStatus())
                .squads(fireEntity.getBrigades().stream()
                        .map(Squad::fromFireBrigadeEntity)
                        .collect(Collectors.toList()))
                .build();
    }

    public static FireEntity toFireEntity(Fire fire) {
        return FireEntity.builder()
                .id(fire.getId())
                .date(fire.getDate())
                .postalCode(fire.getPostalCode())
                .city(fire.getCity())
                .street(fire.getStreet())
                .latitude(fire.getLatitude())
                .longitude(fire.getLongitude())
                .status(fire.getStatus())
                .brigades(fire.getSquads().stream()
                        .map(Squad::toFireBrigadeEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
