package com.spdb.firebase.presentation.fire;

import com.spdb.firebase.domain.fire.Squad;
import com.spdb.firebase.presentation.brigade.BrigadeDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
public class SquadDto {
    private BrigadeDto brigade;
    private Long squadAmount;

    public static SquadDto fromSquad(Squad squad) {
        return SquadDto.builder()
                .brigade(BrigadeDto.fromBrigade(squad.getBrigade()))
                .squadAmount(squad.getSquadAmount())
                .build();
    }
}
