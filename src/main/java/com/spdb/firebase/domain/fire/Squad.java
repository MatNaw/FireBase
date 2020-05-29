package com.spdb.firebase.domain.fire;

import com.spdb.firebase.domain.brigade.Brigade;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Squad {
    private Brigade brigade;
    private Long squadAmount;

    public static Squad fromFireBrigadeEntity(FireBrigadeEntity fireBrigadeEntity) {
        return Squad.builder()
                .brigade(Brigade.fromBrigadeEntity(fireBrigadeEntity.getBrigade()))
                .squadAmount(fireBrigadeEntity.getSquadAmount())
                .build();
    }

    public static FireBrigadeEntity toFireBrigadeEntity(Squad squad) {
        return FireBrigadeEntity.builder()
                .brigade(Brigade.toBrigadeEntity(squad.getBrigade()))
                .squadAmount(squad.getSquadAmount())
                .build();
    }

    public static Squad fromBrigade(Brigade brigade, Long squadAmount) {
        return Squad.builder()
                .brigade(brigade)
                .squadAmount(squadAmount)
                .build();
    }
}
