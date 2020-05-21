package com.spdb.firebase.domain.fire;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FireService {
    private final FireBrigadeService fireBrigadeService;
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

    public Fire addActiveFire(String city,
                              String postalCode,
                              String street,
                              Long brigadesNumber) {

        List<FireBrigadeEntity> fireBrigades = fireBrigadeService.getFireBrigades(brigadesNumber);

        FireEntity fireEntity = FireEntity.builder()
                .city(city)
                .postalCode(postalCode)
                .street(street)
                .date(LocalDate.now())
                .status(Status.ACTIVE)
                .brigades(fireBrigades)
                .build();
        return findFireById(fireRepository.save(fireEntity).getId());
    }
}
