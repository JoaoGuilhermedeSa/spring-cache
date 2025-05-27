package com.example.cache.controller

import com.example.cache.model.CharacterInfo
import com.example.cache.service.CharacterService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CharacterController(
    private val characterService: CharacterService
) {

    @GetMapping("/characters/{name}")
    fun getCharacter(@PathVariable name: String): CharacterInfo {
        return characterService.getCharacter(name)
    }

    @DeleteMapping("/characters/{name}/evict")
    fun evictCharacter(@PathVariable name: String): String {
        characterService.evictCharacter(name)
        return "Cache for $name evicted."
    }
}