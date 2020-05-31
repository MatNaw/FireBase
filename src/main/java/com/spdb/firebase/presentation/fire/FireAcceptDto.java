package com.spdb.firebase.presentation.fire;

import com.spdb.firebase.domain.fire.FireAccept;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
public class FireAcceptDto {
    private String firePlace;
    private Double latitude;
    private Double longitude;
    private List<SquadDto> squads;

    public static FireAcceptDto fromFireAccept(FireAccept fireAccept) {
        return FireAcceptDto.builder()
                .firePlace(fireAccept.getFirePlace())
                .latitude(fireAccept.getLatitude())
                .longitude(fireAccept.getLongitude())
                .squads(fireAccept.getSquads().stream()
                        .map(SquadDto::fromSquad)
                        .collect(Collectors.toList()))
                .build();
    }

    public static FireAccept toFireAccept(FireAcceptDto fireAcceptDto) {
        return FireAccept.builder()
                .firePlace(fireAcceptDto.getFirePlace())
                .latitude(fireAcceptDto.getLatitude())
                .longitude(fireAcceptDto.getLongitude())
                .squads(fireAcceptDto.getSquads().stream()
                        .map(SquadDto::toSquad)
                        .collect(Collectors.toList()))
                .build();
    }
}
