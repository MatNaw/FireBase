package com.spdb.firebase.presentation.fire;

import com.spdb.firebase.domain.fire.Fire;
import com.spdb.firebase.domain.fire.Squad;
import com.spdb.firebase.presentation.brigade.BrigadeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FireMapper {
    private final BrigadeMapper brigadeMapper;

    public FireDto toFireDto(Fire fire) {
        return FireDto.builder()
                .id(fire.getId())
                .date(fire.getDate())
                .postalCode(fire.getPostalCode())
                .city(fire.getCity())
                .street(fire.getStreet())
                .latitude(fire.getLatitude())
                .longitude(fire.getLongitude())
                .status(fire.getStatus())
                .squads(squadsDto(fire.getSquads()))
                .build();
    }

    private List<SquadDto> squadsDto(List<Squad> squads) {
        return squads.stream()
                .map(it -> SquadDto.builder()
                        .brigade(brigadeMapper.toBrigadeDto(it.getBrigade()))
                        .squadAmount(it.getSquadAmount())
                        .build())
                .collect(Collectors.toList());
    }
}
