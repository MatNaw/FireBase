package com.spdb.firebase.system;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.spdb.firebase.system.config.GoogleMapsApiServiceConfiguration;
import com.spdb.firebase.system.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class GoogleMapsApiService {
    private final GoogleMapsApiServiceConfiguration googleMapsApiServiceConfiguration;
    private GeoApiContext context;

    public Pair<Double, Double> getLatitudeLongitude(String city, String postalCode, String street) {
        Double latitude = 0D;
        Double longitude = 0D;
        GeocodingResult[] geocodingResults = getAddressGeocoding(city, postalCode, street);

        if(geocodingResults != null && geocodingResults.length > 0){
            if(geocodingResults[0].geometry != null) {
                latitude = geocodingResults[0].geometry.location.lat;
                longitude = geocodingResults[0].geometry.location.lng;
            }
        }
        return Pair.of(latitude, longitude);
    }

    private GeocodingResult[] getAddressGeocoding(String city, String postalCode, String street){
        if(context == null) {
            context = new GeoApiContext.Builder()
                    .apiKey(googleMapsApiServiceConfiguration.getApiKey())
                    .build();
        }
        try {
            return GeocodingApi.geocode(context, String.format("%s,%s,%s", street, postalCode, city))
                    .await();
        } catch (final IOException ioException) {
            throw new BusinessException("geocoding.ioexception");
        } catch (final InterruptedException interruptedException) {
            throw new BusinessException("geocoding.interruptedexception");
        } catch (final ApiException apiException) {
            throw new BusinessException("geocoding.apiexception");
        }
    }

    public void closeGeoApiContext(){
        context.shutdown();
    }
}
