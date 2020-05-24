package com.spdb.firebase.presentation.fire;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FireRequestDto {
    String city;
    String postalCode;
    String street;
    Long brigadesNumber;
}
