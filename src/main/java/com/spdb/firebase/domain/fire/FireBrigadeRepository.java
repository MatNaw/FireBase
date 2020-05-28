package com.spdb.firebase.domain.fire;

import com.spdb.firebase.domain.brigade.BrigadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FireBrigadeRepository extends JpaRepository<FireBrigadeEntity, Long> {
    List<FireBrigadeEntity> findFireBrigadeEntitiesByBrigade(BrigadeEntity brigade_id);
}
