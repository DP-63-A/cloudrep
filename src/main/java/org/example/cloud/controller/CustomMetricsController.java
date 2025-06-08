package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/metrics")
public class CustomMetricsController {

    @GetMapping("/my-app-stats")
    public Map<String, Object> getAppStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("userCount", 42);
        stats.put("productCount", 13);
        return stats;
    }
}
