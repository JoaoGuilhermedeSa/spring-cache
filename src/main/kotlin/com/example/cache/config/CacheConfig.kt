package com.example.cache.config

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
open class CacheConfig {

    @Bean
    open fun cacheManager(): CacheManager {
        val cache = Caffeine.newBuilder()
            .expireAfterAccess(600, TimeUnit.SECONDS)
            .maximumSize(100)

        val cacheManager = CaffeineCacheManager("characters")
        cacheManager.setCaffeine(cache)
        return cacheManager
    }
}