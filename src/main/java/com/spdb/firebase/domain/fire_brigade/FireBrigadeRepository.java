package com.spdb.firebase.domain.fire_brigade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FireBrigadeRepository extends JpaRepository<FireBrigadeEntity, Long> {
    Optional<FireBrigadeEntity> findById(Long id);
}
