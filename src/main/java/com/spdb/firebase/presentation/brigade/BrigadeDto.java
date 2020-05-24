package com.spdb.firebase.presentation.brigade;

import com.spdb.firebase.domain.brigade.Brigade;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BrigadeDto {
    private Long id;
    private String name;
    private String city;
    private String postalCode;
    private String street;
    private Double latitude;
    private Double longitude;
    private Long squadMaxAmount;

    public static Brigade toBrigade(BrigadeDto brigadeDto) {
        return Brigade.builder()
                .id(brigadeDto.getId())
                .name(brigadeDto.getName())
                .city(brigadeDto.getCity())
                .postalCode(brigadeDto.getPostalCode())
                .street(brigadeDto.getStreet())
                .squadMaxAmount(brigadeDto.getSquadMaxAmount())
                .build();
    }

    public static BrigadeDto fromBrigade(Brigade brigade) {
        return BrigadeDto.builder()
                .id(brigade.getId())
                .name(brigade.getName())
                .city(brigade.getCity())
                .postalCode(brigade.getPostalCode())
                .street(brigade.getStreet())
                .squadMaxAmount(brigade.getSquadMaxAmount())
                .build();
    }
}
