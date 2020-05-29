package com.spdb.firebase.domain.fire;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FireBrigadeRepository extends JpaRepository<FireBrigadeEntity, Long> {
    List<FireBrigadeEntity> findFireBrigadeEntitiesByBrigade_Id(Long brigadeId);
}
