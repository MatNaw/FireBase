package com.spdb.firebase.domain.fire;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FireService {
    private final FireRepository fireRepository;
    private final FireEntityMapper fireEntityMapper;

    public void findAllActiveFires() {
        List<Fire> fires = fireRepository.findAllByStatus(Status.ACTIVE).stream()
                .map(fireEntityMapper::toFire)
                .collect(Collectors.toList());

        System.out.println("xx");
    }
}
