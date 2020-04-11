package com.spdb.firebase.domain.fire_brigade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FireBrigadeService {
    private final FireBrigadeRepository fireBrigadeRepository;
    private final FireBrigadeEntityMapper fireBrigadeEntityMapper;

    public List<FireBrigade> findAllFireBrigades() {
        return fireBrigadeRepository.findAll().stream()
                .map(fireBrigadeEntityMapper::toFireBrigade)
                .collect(Collectors.toList());
    }

    public Long addFireBrigade(FireBrigade brigade) {
        FireBrigadeEntity brigadeEntity = fireBrigadeEntityMapper.toFireBrigadeEntity(brigade);
        return fireBrigadeRepository.save(brigadeEntity).getId();
    }
}
