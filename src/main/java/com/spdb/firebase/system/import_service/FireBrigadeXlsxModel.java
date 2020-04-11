package com.spdb.firebase.system.import_service;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FireBrigadeXlsxModel {
    private String name;
    private String city;
    private String postalCode;
    private String street;
}
