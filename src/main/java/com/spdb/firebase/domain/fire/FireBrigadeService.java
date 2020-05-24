package com.spdb.firebase.domain.fire;

import com.spdb.firebase.domain.brigade.BrigadeEntity;
import com.spdb.firebase.domain.brigade.BrigadeRepository;
import com.spdb.firebase.system.GoogleMapsApiService;
import com.spdb.firebase.system.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FireBrigadeService {
    private final BrigadeRepository brigadeRepository;
    private final GoogleMapsApiService googleMapsApiService;

    @Transactional
    List<FireBrigadeEntity> getFireBrigades(String city,
                                            String postalCode,
                                            String street,
                                            Long brigadesNumber) {
        List<Double> latitudeLongitude = googleMapsApiService.getLatitudeLongitude(city, postalCode, street);

        Double latitude = latitudeLongitude.get(0);
        Double longitude = latitudeLongitude.get(1);

        List<BrigadeEntity> closestBrigades = findBrigadesClosestToFire(latitude, longitude);



        return null;
    }

    private List<BrigadeEntity> findBrigadesClosestToFire(Double latitude, Double longitude) {
        List<BrigadeEntity> brigadeEntities = brigadeRepository.findAll();
        List<Pair<Long, Double>> distances = new ArrayList<>();

        for (BrigadeEntity brigadeEntity : brigadeEntities) {
            distances.add(Pair.of(
                    brigadeEntity.getId(),
                    calculateDistance(
                            latitude,
                            longitude,
                            brigadeEntity.getLatitude(),
                            brigadeEntity.getLongitude()))
            );
        }

        distances.sort(Comparator.comparing(Pair::getSecond));
        //distances.forEach(System.out::println);
        distances.subList(0, 10).forEach(System.out::println);

        List<BrigadeEntity> result = new ArrayList<>();
        distances.subList(0, 10).forEach(pair ->
            brigadeRepository.findById(pair.getFirst())
                    .map(result::add)
                    .orElseThrow(() -> new BusinessException(
                            "Brigade previously found in DB has not been found now, id: " + pair.getFirst()))
        );

        result.forEach(r -> System.out.println(r.getName()));
        return result;
    }

    /**
     * Calculates the distance between two points using the Haversine formula.
     *
     * @param lat1 Latitude of the first point
     * @param long1 Longitude of the first point
     * @param lat2 Latitude of the second point
     * @param long2 Longitude of the second point
     * @return Straight line distance between two coordinates (in kilometers, rounded to two decimal places)
     */
    private Double calculateDistance(Double lat1, Double long1,
                                     Double lat2, Double long2) {
        if ((lat1.equals(lat2)) && (long1.equals(long2))) {
            return 0D;
        }

        final int earthRadius = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double longDistance = Math.toRadians(long2 - long1);
        double haversine = Math.pow(Math.sin(latDistance / 2), 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.pow(Math.sin(longDistance / 2), 2);
        double archaversine = 2 * Math.atan2(Math.sqrt(haversine), Math.sqrt(1 - haversine));

        return Math.round(earthRadius * archaversine * 100.0) / 100.0;
    }
}
