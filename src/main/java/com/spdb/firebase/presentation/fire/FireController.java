package com.spdb.firebase.presentation.fire;

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
    private final FireMapper fireMapper;

    @GetMapping("/active")
    public ResponseEntity<List<FireDto>> getAllActiveFires() {
        return ResponseEntity.ok(
                fireService.findAllActiveFires().stream()
                .map(fireMapper::toFireDto)
                .collect(Collectors.toList())
        );
    }

    @PostMapping("/report")
    public ResponseEntity<FireDto> getFireRequest(@RequestBody FireRequestDto fireRequestDto) {
        return ResponseEntity.ok(
                fireMapper.toFireDto(
                        fireService.addActiveFire(
                                fireRequestDto.getCity(),
                                fireRequestDto.getPostalCode(),
                                fireRequestDto.getStreet(),
                                fireRequestDto.getBrigadesNumber()))
        );
    }
}
