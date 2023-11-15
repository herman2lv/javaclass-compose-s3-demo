package com.hrm.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/resources")
@RequiredArgsConstructor
public class AppController {

    private final AppService appService;

    @GetMapping("/test")
    public String get() {
        log.info("Controller invoked");
        return "Hello World - Resources!";
    }

    @GetMapping("{id}")
    public EntityObject get(@PathVariable Long id) {
        return appService.get(id);
    }

    @GetMapping
    public List<EntityObject> getAll() {
        return appService.getAll();
    }

    @PostMapping
    public EntityObject save(@RequestBody EntityObject entity) {
        return appService.save(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        appService.delete(id);
    }
}
