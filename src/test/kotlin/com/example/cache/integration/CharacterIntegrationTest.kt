package com.example.cache.integration

import com.example.cache.model.CharacterInfo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.assertj.core.api.Assertions.assertThat

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CharacterIntegrationTest {

    @LocalServerPort
    private var port: Int = 0

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun `should cache character response and return identical result`() {
        val name = "Cachero"
        val url = "http://localhost:$port/characters/$name"

        val response1 = restTemplate.getForEntity(url, CharacterInfo::class.java)
        val response2 = restTemplate.getForEntity(url, CharacterInfo::class.java)

        assertThat(response1.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response2.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response1.body).isEqualTo(response2.body)
    }

    @Test
    fun `should evict character cache and return different data`() {
        val name = "Cachero"
        val url = "http://localhost:$port/characters/$name"
        val evictUrl = "http://localhost:$port/characters/$name/evict"

        val response1 = restTemplate.getForEntity(url, CharacterInfo::class.java)
        restTemplate.delete(evictUrl)
        val response2 = restTemplate.getForEntity(url, CharacterInfo::class.java)

        assertThat(response1.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response2.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response1.body).isNotEqualTo(response2.body)
    }
}