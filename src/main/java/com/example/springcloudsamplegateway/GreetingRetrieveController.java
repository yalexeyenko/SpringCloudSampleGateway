package com.example.springcloudsamplegateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/hithere")
public class GreetingRetrieveController {

    @GetMapping
    public ResponseEntity<String> getGreeting() {
        log.info("GreetingRetrieveController is running.");
        return ResponseEntity.ok("Hi there!");
    }
}
