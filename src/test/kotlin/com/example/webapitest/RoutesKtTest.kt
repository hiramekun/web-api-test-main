package com.example.webapitest

import com.example.webapitest.model.User
import com.example.webapitest.service.UserService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import io.ktor.server.testing.*
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UserRoutesTest {

    private val id = 1L
    private val user = User(id, 1L, "John Doe", "john.doe@example.com", "password", true)
    private val userServiceMock: UserService = mockk {
        every { getByOrNull(id) } returns user
        every { getByOrNull(9999L) } returns null
    }

    private fun setupTestApplication(): Application.() -> Unit = {
        install(ContentNegotiation) {
            jackson()
        }

        routing {
            userRoutes(userServiceMock)
        }
    }

    @Test
    fun `test userRoutes for valid ID`() {
        withTestApplication(setupTestApplication()) {
            val response = handleRequest(HttpMethod.Get, "/users/1")
            val objectMapper = jacksonObjectMapper()
            val deserializedUser: User = objectMapper.readValue(response.response.content!!)

            assertEquals(user, deserializedUser)
            assertEquals(HttpStatusCode.OK, response.response.status())
        }
    }

    @Test
    fun `test userRoutes for invalid ID`() {
        withTestApplication(setupTestApplication()) {
            val request = handleRequest(HttpMethod.Get, "/users/invalid")
            assertEquals(HttpStatusCode.BadRequest, request.response.status())
        }
    }

    @Test
    fun `test userRoutes for non-existent ID`() {
        withTestApplication(setupTestApplication()) {
            val request = handleRequest(HttpMethod.Get, "/users/9999")
            assertEquals(HttpStatusCode.NotFound, request.response.status())
        }
    }
}
