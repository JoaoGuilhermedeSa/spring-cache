package com.example.cache.service

import com.example.cache.model.CharacterInfo
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
open class CharacterService {

    private val vocations = listOf("Knight", "Elite Knight", "Paladin", "Royal Paladin", "Sorcerer", "Master Sorcerer", "Druid", "Elder Druid")
    private val worlds = listOf("Antica", "Premia", "Harmonia", "Ferobra", "Secura", "Lobera", "Quelibra")

    @Cacheable("characters")
    open fun getCharacter(name: String): CharacterInfo {
        println("Fetching data from simulated 'Tibia API' for $name...")
        Thread.sleep(3000) // Simula requisição demorada
        return CharacterInfo(
            name = name,
            vocation = vocations.random(),
            level = (8..500).random(),
            world = worlds.random()
        )
    }

    @CacheEvict("characters", key = "#name")
    open fun evictCharacter(name: String) {
        println("Evicting cache for $name")
    }
}