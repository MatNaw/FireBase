package com.spdb.firebase.presentation.brigade;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BrigadeDto {
    private Long id;
    private String name;
    private String city;
    private String postalCode;
    private String street;
    private Long squadMaxAmount;
}
