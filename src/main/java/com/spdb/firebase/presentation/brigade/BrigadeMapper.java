package com.spdb.firebase.presentation.brigade;

import com.spdb.firebase.domain.brigade.Brigade;
import org.springframework.stereotype.Component;

@Component
public class BrigadeMapper {

    public Brigade toBrigade(BrigadeDto brigadeDto) {
        return Brigade.builder()
                .id(brigadeDto.getId())
                .name(brigadeDto.getName())
                .city(brigadeDto.getCity())
                .postalCode(brigadeDto.getPostalCode())
                .street(brigadeDto.getStreet())
                .squadMaxAmount(brigadeDto.getSquadMaxAmount())
                .build();
    }

    public BrigadeDto toBrigadeDto(Brigade brigade) {
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
