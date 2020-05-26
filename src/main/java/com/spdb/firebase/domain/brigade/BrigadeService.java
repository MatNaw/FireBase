package com.spdb.firebase.domain.brigade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrigadeService {
    private final BrigadeRepository brigadeRepository;

    @Transactional
    public List<Brigade> findAllBrigades() {
        return brigadeRepository.findAll().stream()
                .map(Brigade::fromBrigadeEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long addBrigade(Brigade brigade) {
        BrigadeEntity brigadeEntity = Brigade.toBrigadeEntity(brigade);
        return brigadeRepository.save(brigadeEntity).getId();
    }
}
