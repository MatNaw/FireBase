package com.spdb.firebase.domain.brigade;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Brigade {
    private Long id;
    private String name;
    private String city;
    private String postalCode;
    private String street;
    private Long squadMaxAmount;
}
