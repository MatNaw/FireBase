package com.spdb.firebase.domain.fire_brigade;

import org.springframework.stereotype.Component;

@Component
public class FireBrigadeEntityMapper {

    public FireBrigade toFireBrigade(FireBrigadeEntity fireBrigadeEntity) {
        return FireBrigade.builder()
                .id(fireBrigadeEntity.getId())
                .name(fireBrigadeEntity.getName())
                .city(fireBrigadeEntity.getCity())
                .postalCode(fireBrigadeEntity.getPostalCode())
                .street(fireBrigadeEntity.getStreet())
                .squadAmount(fireBrigadeEntity.getSquadAmount())
                .build();
    }

    public FireBrigadeEntity toFireBrigadeEntity(FireBrigade fireBrigade) {
        return FireBrigadeEntity.builder()
                .id(fireBrigade.getId())
                .name(fireBrigade.getName())
                .city(fireBrigade.getCity())
                .postalCode(fireBrigade.getPostalCode())
                .street(fireBrigade.getStreet())
                .squadAmount(fireBrigade.getSquadAmount())
                .build();
    }
}
