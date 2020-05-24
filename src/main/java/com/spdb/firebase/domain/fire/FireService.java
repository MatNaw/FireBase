package com.spdb.firebase.domain.fire;

import com.spdb.firebase.system.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FireService {
    private final FireBrigadeService fireBrigadeService;
    private final FireRepository fireRepository;

    @Transactional
    public List<Fire> findAllActiveFires() {
        return fireRepository.findAllByStatus(Status.ACTIVE).stream()
                .map(Fire::fromFireEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public Fire processFireRequest(String city,
                                   String postalCode,
                                   String street,
                                   Long brigadesNumber) {
        List<FireBrigadeEntity> fireBrigades = fireBrigadeService.getFireBrigades(city, postalCode, street, brigadesNumber);
        FireEntity fireEntity = FireEntity.builder()
                .city(city)
                .postalCode(postalCode)
                .street(street)
                .date(LocalDate.now())
                .status(Status.ACTIVE)
                .brigades(fireBrigades)
                .build();
        return fireEntityMapper.toFire(fireEntity);
    }

    @Transactional
    public Fire addActiveFire(String city,
                              String postalCode,
                              String street,
                              List<FireBrigadeEntity> fireBrigades) {
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

    private Fire findFireById (Long id) {
        return fireRepository.findById(id)
                .map(fireEntityMapper::toFire)
                .orElseThrow(() -> new BusinessException("Error - fire does not exist"));
    }
}
