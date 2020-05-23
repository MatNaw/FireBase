package com.spdb.firebase.presentation.fire;

import com.spdb.firebase.domain.fire.Fire;
import com.spdb.firebase.domain.fire.Squad;
import com.spdb.firebase.domain.fire.Status;
import com.spdb.firebase.presentation.brigade.BrigadeDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
public class FireDto {
    private Long id;
    private String city;
    private String postalCode;
    private String street;
    private LocalDate date;
    private Status status;
    private List<SquadDto> squads;

    public static FireDto fromFire(Fire fire) {
        return FireDto.builder()
                .id(fire.getId())
                .date(fire.getDate())
                .postalCode(fire.getPostalCode())
                .city(fire.getCity())
                .street(fire.getStreet())
                .status(fire.getStatus())
                .squads(fire.getSquads().stream()
                        .map(SquadDto::fromSquad)
                        .collect(Collectors.toList()))
                .build();
    }
}
