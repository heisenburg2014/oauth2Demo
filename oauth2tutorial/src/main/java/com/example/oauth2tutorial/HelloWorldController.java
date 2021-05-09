package com.example.oauth2tutorial;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;


@Controller
public class HelloWorldController {
    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "Hello World";
    }


    @GetMapping("/restricted")
    public String restricted(Authentication authentication) {
        System.out.println("All attributes - " + authentication.getPrincipal());
        return "to see this text you need to be logged in!";
    }
}
