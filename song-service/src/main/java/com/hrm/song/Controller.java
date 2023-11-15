package com.hrm.song;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Controller {

    @GetMapping
    public String get() {
        log.info("Controller invoked");
        return "Hello World - Songs!";
    }
}
