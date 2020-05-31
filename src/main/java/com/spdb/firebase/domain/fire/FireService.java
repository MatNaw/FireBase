package com.spdb.firebase.domain.fire;

import com.spdb.firebase.system.exception.BusinessException;
import jdk.jshell.Snippet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FireService {
    private final FireRepository fireRepository;
    private final FireBrigadeRepository fireBrigadeRepository;
    private final FireBrigadeService fireBrigadeService;

    @Transactional
    public List<Fire> findAllActiveFires() {
        return fireRepository.findAllByStatus(Status.ACTIVE).stream()
                .map(Fire::fromFireEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public Fire cancelFire(Long fireId) {
        FireEntity fireEntity = fireRepository.findById(fireId)
                .orElseThrow(() -> new BusinessException("fire-not-exists"));
        fireEntity.setStatus(Status.INACTIVE);
        fireRepository.save(fireEntity);
        return Fire.fromFireEntity(fireEntity);
    }

    @Transactional
    public Long acceptFire(FireAccept fireAccept) {
        validateCurrentSquadsAvailability(fireAccept);
        validateExistenceOfFire(fireAccept);

        FireEntity fireEntity = saveFire(fireAccept);
        saveFireBrigades(fireAccept, fireEntity);

        return fireEntity.getId();
    }

    @Transactional
    FireEntity saveFire(FireAccept fireAccept) {
        FirePlaceUtil firePlace = FirePlaceUtil.parseFirePlace(fireAccept.getFirePlace());

        FireEntity fireEntity = FireEntity.builder()
                .city(firePlace.getCity())
                .postalCode(firePlace.getPostalCode())
                .street(firePlace.getStreet())
                .latitude(fireAccept.getLatitude())
                .longitude(fireAccept.getLongitude())
                .date(LocalDate.now())
                .status(Status.ACTIVE)
                .build();

        fireRepository.save(fireEntity);
        return fireEntity;
    }

    @Transactional
    List<FireBrigadeEntity> saveFireBrigades(FireAccept fireAccept, FireEntity fireEntity) {
        List<FireBrigadeEntity> fireBrigadeEntities = fireAccept.getSquads().stream()
                .filter(squad -> squad.getSquadAmount() != null && squad.getSquadAmount() > 0)
                .map(Squad::toFireBrigadeEntity)
                .collect(Collectors.toList());

        fireBrigadeEntities.forEach(fireBrigadeEntity -> {
            fireBrigadeEntity.setId(new FireBrigadeEntityId(
                    fireEntity.getId(),
                    fireBrigadeEntity.getBrigade().getId()));

            fireBrigadeEntity.setFire(fireEntity);
            fireBrigadeRepository.save(fireBrigadeEntity);
        });
        return fireBrigadeEntities;
    }

    private void validateCurrentSquadsAvailability(FireAccept fireAccept) {
        fireAccept.getSquads().forEach(squad -> {
            if(squad.getSquadAmount() > fireBrigadeService.getAvailableSquadsAmount(squad.getBrigade())) {
                throw new BusinessException("squad-amount-changed");
            }
        });
    }

    private void validateExistenceOfFire(FireAccept fireAccept) {
        Optional<FireEntity> fireEntity = fireRepository.findByLatitudeAndLongitudeAndStatus(
                fireAccept.getLatitude(),
                fireAccept.getLongitude(),
                Status.ACTIVE);

        if(fireEntity.isPresent() && fireEntity.get().getStatus().equals(Status.ACTIVE)) {
            throw new BusinessException("fire-already-exists");
        }
    }
}
