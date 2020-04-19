package com.spdb.firebase.presentation.fire;

import com.spdb.firebase.domain.fire.FireService;
import com.spdb.firebase.system.Endpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(FireController.FIRE_URI)
public class FireController {
    static final String FIRE_URI = Endpoint.API_ROOT + "/fire";
    private final FireService fireService;

    @GetMapping("/active")
    public void getAllActiveFires() {
        fireService.findAllActiveFires();
    }
}
