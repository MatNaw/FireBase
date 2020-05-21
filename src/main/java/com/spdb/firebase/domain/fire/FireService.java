package com.spdb.firebase.domain.fire;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FireService {
    private final FireRepository fireRepository;
    private final FireEntityMapper fireEntityMapper;

    public List<Fire> findAllActiveFires() {
        return fireRepository.findAllByStatus(Status.ACTIVE).stream()
                .map(fireEntityMapper::toFire)
                .collect(Collectors.toList());
    }

    public Fire findFireById (Long id) {
        FireEntity fireEntity = fireRepository.findById(id).orElse(null);
        return fireEntity != null ? fireEntityMapper.toFire(fireEntity) : null;
    }

    public Fire addActiveFire(@RequestParam("city") String city,
                              @RequestParam("postal_code") String postal_code,
                              @RequestParam("street") String street) {

        FireEntity fireEntity = FireEntity.builder()
                .city(city)
                .postalCode(postal_code)
                .street(street)
                .date(LocalDate.now())
                .status(Status.ACTIVE)
                .build();
        return findFireById(fireRepository.save(fireEntity).getId());
    }
}
