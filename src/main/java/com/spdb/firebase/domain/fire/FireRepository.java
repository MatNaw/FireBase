package com.spdb.firebase.domain.fire;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FireRepository extends JpaRepository<FireEntity, Long> {
    List<FireEntity> findAllByStatus(Status status);
}
