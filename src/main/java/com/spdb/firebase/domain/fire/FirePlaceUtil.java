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
                String[] postalCodeWithCity;
                if(address[1].equals("Polska")) {
                    street = "";
                    postalCodeWithCity = parsePostalCodeWithCity(address[0]);
                }
                else {
                    postalCodeWithCity = parsePostalCodeWithCity(address[1]);
                }

                if (postalCodeWithCity.length > 1) {
                    postalCode = postalCodeWithCity[0];
                    city = postalCodeWithCity[1];
                } else if (postalCodeWithCity.length > 0) {
                    if (postalCodeWithCity[0].matches("\\d+-\\d+")) {
                        postalCode = postalCodeWithCity[0];
                        city = address[0].split(" \\d+")[0];
                    } else {
                        city = postalCodeWithCity[0];
                    }
                }
            }
        }

        return FirePlaceUtil.builder()
                .street(street)
                .postalCode(postalCode)
                .city(city)
                .build();
    }

    static String[] parsePostalCodeWithCity(String postalCodeWithCity) {
        return postalCodeWithCity.split(" ", 2);
    }
}
