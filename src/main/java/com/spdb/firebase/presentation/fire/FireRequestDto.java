package com.spdb.firebase.presentation.fire;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FireRequestDto {
    String city;
    String postalCode;
    String street;
    Long brigadesNumber;
}
