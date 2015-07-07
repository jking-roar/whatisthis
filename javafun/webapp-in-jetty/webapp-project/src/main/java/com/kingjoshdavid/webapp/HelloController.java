package com.kingjoshdavid.webapp;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/greet/{name}")
    public ResponseEntity<String> greet(@PathVariable String name) {
        String greeting = "Hello, " + name;
        return ResponseEntity.ok(greeting);
    }
}
