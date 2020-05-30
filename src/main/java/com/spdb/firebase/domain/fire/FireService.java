package com.spdb.firebase.domain.fire;

import com.spdb.firebase.presentation.fire.FireAcceptDto;
import com.spdb.firebase.presentation.fire.SquadDto;
import com.spdb.firebase.system.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FireService {
    private final FireRepository fireRepository;
    private final FireBrigadeRepository fireBrigadeRepository;

    @Transactional
    public List<Fire> findAllActiveFires() {
        return fireRepository.findAllByStatus(Status.ACTIVE).stream()
                .map(Fire::fromFireEntity)
                .collect(Collectors.toList());
    }

    public Fire cancelFire(Long fireId) {
        FireEntity fireEntity = fireRepository.findById(fireId)
                .orElseThrow(() -> new BusinessException("fire.not-exists"));
        fireEntity.setStatus(Status.INACTIVE);
        fireRepository.save(fireEntity);
        return Fire.fromFireEntity(fireEntity);
    }

    public Fire acceptFire(FireAcceptDto fireAcceptDto) {
        FireEntity fireEntity = saveFire(fireAcceptDto);
        fireEntity.setBrigades(saveFireBrigades(fireAcceptDto, fireEntity));

        return Fire.fromFireEntity(fireEntity);
    }

    @Transactional
    FireEntity saveFire(FireAcceptDto fireAcceptDto) {
        FirePlaceUtil firePlace = FirePlaceUtil.parseFirePlace(fireAcceptDto.getFirePlace());

        FireEntity fireEntity = FireEntity.builder()
                .city(firePlace.getCity())
                .postalCode(firePlace.getPostalCode())
                .street(firePlace.getStreet())
                .latitude(fireAcceptDto.getLatitude())
                .longitude(fireAcceptDto.getLongitude())
                .date(LocalDate.now())
                .status(Status.ACTIVE)
                .build();

        fireRepository.save(fireEntity);
        return fireEntity;
    }

    @Transactional
    List<FireBrigadeEntity> saveFireBrigades(FireAcceptDto fireAcceptDto, FireEntity fireEntity) {
        List<FireBrigadeEntity> fireBrigadeEntities = fireAcceptDto.getSquads().stream()
                .map(SquadDto::toSquad)
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
}
