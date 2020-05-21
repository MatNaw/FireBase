package com.spdb.firebase.system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@NoArgsConstructor
public class GoogleMapsApiService {
    private static final String API_KEY = "AIzaSyAaqPB_iJm8kbJCfJHaSljjVWG_K5jrtPY";
    private static final GeoApiContext context = new GeoApiContext.Builder()
            .apiKey(API_KEY)
            .build();

    public GeocodingResult[] getLatitudeLongitude(String city, String postalCode, String street){
        try {
            return GeocodingApi.geocode(context, String.format("%s,%s,%s", street, postalCode, city))
                    .await();
        } catch (final IOException ioException) {
            log.info("IOException caught");
        } catch (final InterruptedException interruptedException) {
            log.info("InterruptedException caught");
        } catch (final ApiException apiException) {
            log.info("ApiException caught");
        }
        return null;
    }

    public void closeGeoApiContext(){
        context.shutdown();
    }
}
