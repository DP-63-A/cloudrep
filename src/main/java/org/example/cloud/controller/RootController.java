package org.example.cloud.controller;

@RestController
public class RootController {

    @GetMapping("/")
    public String home() {
        return "App is up and running!";
    }
}
