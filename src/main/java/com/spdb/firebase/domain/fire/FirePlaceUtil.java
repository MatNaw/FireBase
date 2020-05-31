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
        String street = "", postalCode = "", city = "";
        String[] address = firePlace.split(", ");

        if(address.length > 0) {
            street = address[0];

            if(address.length > 1) {
                String[] postalCodeWithCity = address[1].split(" ", 2);
                if(postalCodeWithCity.length > 1) {
                    postalCode = postalCodeWithCity[0];
                    city = postalCodeWithCity[1];
                }
            }
        }

        return FirePlaceUtil.builder()
                .street(street)
                .postalCode(postalCode)
                .city(city)
                .build();
    }
}
