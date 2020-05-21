package com.spdb.firebase.domain.fire;

import com.spdb.firebase.domain.brigade.BrigadeEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FireEntityMapper {
    private final BrigadeEntityMapper brigadeEntityMapper;

    public Fire toFire(FireEntity fireEntity) {
        return Fire.builder()
                .id(fireEntity.getId())
                .date(fireEntity.getDate())
                .postalCode(fireEntity.getPostalCode())
                .city(fireEntity.getCity())
                .street(fireEntity.getStreet())
                .latitude(fireEntity.getLatitude())
                .longitude(fireEntity.getLongitude())
                .status(fireEntity.getStatus())
                .squads(squads(fireEntity.getBrigades()))
                .build();
    }

    private List<Squad> squads(List<FireBrigadeEntity> fireBrigadeEntities) {
        return fireBrigadeEntities.stream()
                .map(it -> Squad.builder()
                        .brigade(brigadeEntityMapper.toBrigade(it.getBrigade()))
                        .squadAmount(it.getSquadAmount())
                        .build())
                .collect(Collectors.toList());
    }
}
