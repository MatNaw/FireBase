package com.spdb.firebase.domain.fire;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class FireAccept {
    private String firePlace;
    private Double latitude;
    private Double longitude;
    private List<Squad> squads;
}
