package com.spdb.firebase.domain.fire;

import com.spdb.firebase.domain.brigade.BrigadeEntity;
import com.spdb.firebase.system.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FireService {
    private final FireRepository fireRepository;

    @Transactional
    public List<Fire> findAllActiveFires() {
        return fireRepository.findAllByStatus(Status.ACTIVE).stream()
                .map(Fire::fromFireEntity)
                .collect(Collectors.toList());
    }
}
