package com.spdb.firebase.presentation.brigade;

import com.spdb.firebase.domain.brigade.BrigadeService;
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
@RequestMapping(BrigadeController.BRIGADE_URI)
public class BrigadeController {

    static final String BRIGADE_URI = Endpoint.API_ROOT + "/brigade";
    private final BrigadeService brigadeService;
    private final BrigadeMapper brigadeMapper;

    @GetMapping
    public ResponseEntity<List<BrigadeDto>> getAllBrigades() {
        return ResponseEntity.ok(
                brigadeService.findAllBrigades().stream()
                        .map(brigadeMapper::toBrigadeDto)
                        .collect(Collectors.toList())
        );
    }
}
