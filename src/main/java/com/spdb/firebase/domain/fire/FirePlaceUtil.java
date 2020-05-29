package com.spdb.firebase.domain.fire;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FirePlaceUtil {
    String city;
    String postalCode;
    String street;

    public static FirePlaceUtil parseFirePlace(String firePlace) {
        if(firePlace != null){
            String[] address = firePlace.split(", ");
            if(address.length > 1) {
                String[] postalCodeWithCity = address[1].split(" ", 1);

                return FirePlaceUtil.builder()
                        .street(address[0])
                        .postalCode(postalCodeWithCity[0])
                        .city(postalCodeWithCity[1])
                        .build();
            }
            else if(address.length > 0) {
                return FirePlaceUtil.builder()
                        .street(address[0])
                        .postalCode("")
                        .city("")
                        .build();
            }
        }
        return FirePlaceUtil.builder()
                .street("")
                .postalCode("")
                .city("")
                .build();
    }
}
