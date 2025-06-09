package org.example.cloud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/debug")
@RequiredArgsConstructor
public class CacheDebugController {

    private final CacheManager cacheManager;

    @GetMapping("/cache/users")
    public Object getUsersCacheContent() {
        CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache("users");
        if (caffeineCache != null) {
            return caffeineCache.getNativeCache().asMap();
        }
        return Map.of("error", "Cache 'users' not found");
    }
}
