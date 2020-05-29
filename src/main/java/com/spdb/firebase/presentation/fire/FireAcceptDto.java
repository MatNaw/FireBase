package com.spdb.firebase.presentation.fire;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class FireAcceptDto {
    private String firePlace;
    private Double latitude;
    private Double longitude;
    private List<SquadDto> squads;
}
