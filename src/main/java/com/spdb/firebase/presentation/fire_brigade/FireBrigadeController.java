package com.spdb.firebase.presentation.fire_brigade;

import com.spdb.firebase.domain.fire_brigade.FireBrigadeService;
import com.spdb.firebase.system.Endpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(FireBrigadeController.FIRE_BRIGADE_URI)
public class FireBrigadeController {

    static final String FIRE_BRIGADE_URI = Endpoint.API_ROOT + "/fire-brigade";
    private final FireBrigadeService fireBrigadeService;
    private final FireBrigadeMapper fireBrigadeMapper;

    @GetMapping
    public ResponseEntity<List<FireBrigadeDto>> getAllFireBrigades() {
        return ResponseEntity.ok(
                fireBrigadeService.findAllFireBrigades().stream()
                        .map(fireBrigadeMapper::toFireBrigadeDto)
                        .collect(Collectors.toList())
        );
    }
}
