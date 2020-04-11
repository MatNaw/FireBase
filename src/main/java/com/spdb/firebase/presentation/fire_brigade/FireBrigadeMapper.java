package com.spdb.firebase.presentation.fire_brigade;

import com.spdb.firebase.domain.fire_brigade.FireBrigade;
import org.springframework.stereotype.Component;

@Component
public class FireBrigadeMapper {

    public FireBrigade toFireBrigade(FireBrigadeDto fireBrigadeEntity) {
        return FireBrigade.builder()
                .id(fireBrigadeEntity.getId())
                .name(fireBrigadeEntity.getName())
                .city(fireBrigadeEntity.getCity())
                .postalCode(fireBrigadeEntity.getPostalCode())
                .street(fireBrigadeEntity.getStreet())
                .squadAmount(fireBrigadeEntity.getSquadAmount())
                .build();
    }

    public FireBrigadeDto toFireBrigadeDto(FireBrigade fireBrigade) {
        return FireBrigadeDto.builder()
                .id(fireBrigade.getId())
                .name(fireBrigade.getName())
                .city(fireBrigade.getCity())
                .postalCode(fireBrigade.getPostalCode())
                .street(fireBrigade.getStreet())
                .squadAmount(fireBrigade.getSquadAmount())
                .build();
    }
}
