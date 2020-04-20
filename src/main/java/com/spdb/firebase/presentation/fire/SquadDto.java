package com.spdb.firebase.presentation.fire;

import com.spdb.firebase.presentation.brigade.BrigadeDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SquadDto {
    private BrigadeDto brigade;
    private Long squadAmount;
}
