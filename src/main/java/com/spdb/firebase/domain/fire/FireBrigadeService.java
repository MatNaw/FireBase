package com.spdb.firebase.domain.fire;

import com.spdb.firebase.domain.brigade.Brigade;
import com.spdb.firebase.domain.brigade.BrigadeEntity;
import com.spdb.firebase.domain.brigade.BrigadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FireBrigadeService {
    private static final Integer SUGGESTED_NUMBER_OF_BRIGADES = 10;
    private final BrigadeRepository brigadeRepository;
    private final FireBrigadeRepository fireBrigadeRepository;

    @Transactional
    public List<Squad> processFireRequest(Double latitude, Double longitude) {
        return findBrigadesClosestToFire(latitude, longitude).stream()
                .map(Brigade::fromBrigadeEntity)
                .map(brigade -> Squad.fromBrigade(brigade, getAvailableSquadsAmount(brigade)))
                .collect(Collectors.toList());
    }

    public Long getAvailableSquadsAmount(Brigade brigade) {
        Long occupied = fireBrigadeRepository.findFireBrigadeEntitiesByBrigade_Id(brigade.getId())
                .stream()
                .mapToLong(FireBrigadeEntity::getSquadAmount)
                .sum();
        return Math.max(brigade.getSquadMaxAmount() - occupied, 0);
    }

    private List<BrigadeEntity> findBrigadesClosestToFire(Double latitude, Double longitude) {
        List<Pair<BrigadeEntity, Double>> distances = brigadeRepository.findAll().stream()
                .map(it -> Pair.of(it,
                        calculateDistance(latitude, longitude, it.getLatitude(), it.getLongitude())))
                .sorted(Comparator.comparing(Pair::getSecond))
                .collect(Collectors.toList());

        return distances.subList(0, SUGGESTED_NUMBER_OF_BRIGADES).stream()
                .map(Pair::getFirst)
                .collect(Collectors.toList());
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
    private Double calculateDistance(Double lat1, Double long1, Double lat2, Double long2) {
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
