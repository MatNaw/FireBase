package com.spdb.firebase.domain.fire;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FireBrigadeService {
    @Transactional
    List<FireBrigadeEntity> getFireBrigades(Long brigadesNumber) {
        return null;
    }
}
