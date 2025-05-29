package com.example.cache.controller

import com.example.cache.model.CharacterInfo
import com.example.cache.service.CharacterService
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.delete
import org.mockito.Mockito.*
import org.springframework.http.MediaType

@WebMvcTest(CharacterController::class)
class CharacterControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var characterService: CharacterService

    @Test
    fun `should return character info from service`() {
        val character = CharacterInfo("Ekzura", "Elite Knight", 100, "Premia")
        `when`(characterService.getCharacter("Ekzura")).thenReturn(character)

        mockMvc.get("/characters/Ekzura")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.name") { value("Ekzura") }
                jsonPath("$.vocation") { value("Elite Knight") }
                jsonPath("$.level") { value(100) }
                jsonPath("$.world") { value("Premia") }
            }
    }

    @Test
    fun `should evict character cache`() {
        doNothing().`when`(characterService).evictCharacter("Ekzura")

        mockMvc.delete("/characters/Ekzura/evict")
            .andExpect {
                status { isOk() }
            }
    }
}