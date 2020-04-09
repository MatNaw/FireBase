package com.spdb.firebase.presentation.test;

import com.spdb.firebase.system.Endpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TestResource.TEST_URI)
class TestResource {

    static final String TEST_URI = Endpoint.API_ROOT + "/test";

    @GetMapping
    public String hello() {
        return "hello world";
    }

}
