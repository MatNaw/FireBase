package com.spdb.firebase.presentation.fire;

import com.spdb.firebase.domain.fire.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
public class FireDto {
    private Long id;
    private String city;
    private String postalCode;
    private String street;
    private Double latitude;
    private Double longitude;
    private LocalDate date;
    private Status status;
    private List<SquadDto> squads;
}
