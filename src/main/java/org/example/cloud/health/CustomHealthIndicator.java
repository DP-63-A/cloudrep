package org.example.cloud.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        return Health.up()
                .withDetail("uptime", ManagementFactory.getRuntimeMXBean().getUptime())
                .withDetail("custom", "Everything works fine")
                .build();
    }
}
