package com.spdb.firebase.presentation.fire;

import com.spdb.firebase.domain.fire.FireBrigadeService;
import com.spdb.firebase.domain.fire.FireService;
import com.spdb.firebase.system.Endpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(FireController.FIRE_URI)
public class FireController {
    static final String FIRE_URI = Endpoint.API_ROOT + "/fire";
    private final FireService fireService;
    private final FireBrigadeService fireBrigadeService;

    @GetMapping("/active")
    public ResponseEntity<List<FireDto>> getAllActiveFires() {
        return ResponseEntity.ok(
                fireService.findAllActiveFires().stream()
                .map(FireDto::fromFire)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/report")
    public ResponseEntity<List<SquadDto>> getFireRequest(@RequestParam Double latitude,
                                                         @RequestParam Double longitude,
                                                         @RequestParam Long brigadesNumber) {
        return ResponseEntity.ok(
                fireBrigadeService.processFireRequest(latitude, longitude, brigadesNumber).stream()
                .map(SquadDto::fromSquad)
                .collect(Collectors.toList())
        );
    }
}
