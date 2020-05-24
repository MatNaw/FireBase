package com.spdb.firebase.system;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.spdb.firebase.system.exception.BusinessException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
@NoArgsConstructor
public class GoogleMapsApiService {
    private static final String API_KEY = "AIzaSyAaqPB_iJm8kbJCfJHaSljjVWG_K5jrtPY";
    private static final GeoApiContext context = new GeoApiContext.Builder()
            .apiKey(API_KEY)
            .build();

    public List<Double> getLatitudeLongitude(String city, String postalCode, String street) {
        Double latitude = 0D;
        Double longitude = 0D;
        GeocodingResult[] geocodingResults = getAddressGeocoding(city, postalCode, street);

        if(geocodingResults != null && geocodingResults.length > 0){
            if(geocodingResults[0].geometry != null) {
                latitude = geocodingResults[0].geometry.location.lat;
                longitude = geocodingResults[0].geometry.location.lng;
            }
        }

        ArrayList<Double> latitudeLongitude = new ArrayList<>();
        Collections.addAll(latitudeLongitude, latitude, longitude);
        return latitudeLongitude;
    }

    private GeocodingResult[] getAddressGeocoding(String city, String postalCode, String street){
        try {
            return GeocodingApi.geocode(context, String.format("%s,%s,%s", street, postalCode, city))
                    .await();
        } catch (final IOException ioException) {
            throw new BusinessException("IOException caught while requesting address geocoding to Geocoding API");
        } catch (final InterruptedException interruptedException) {
            throw new BusinessException("InterruptedException caught while requesting address geocoding to Geocoding API");
        } catch (final ApiException apiException) {
            throw new BusinessException("ApiException caught while requesting address geocoding to Geocoding API");
        }
    }

    public void closeGeoApiContext(){
        context.shutdown();
    }
}
