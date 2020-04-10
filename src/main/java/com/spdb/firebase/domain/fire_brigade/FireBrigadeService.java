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
        List<FireBrigadeEntity> tmp = fireBrigadeRepository.findAll();

        return tmp.stream()
                .map(fireBrigadeEntityMapper::toFireBrigade)
                .collect(Collectors.toList());
    }
}
