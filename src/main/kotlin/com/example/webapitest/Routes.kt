package com.example.webapitest

import com.example.webapitest.service.UserService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes(userService: UserService) {
    route("/users") {
        get("/{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
            id?.let {
                userService.getByOrNull(it)?.let { user -> call.respond(HttpStatusCode.OK, user) }
                    ?: call.respond(
                        HttpStatusCode.NotFound,
                        "Unable to locate a user with the specified ID. Please ensure the provided ID is accurate and the user exists in the system."
                    )
            } ?: call.respond(HttpStatusCode.BadRequest, "Invalid user ID")
        }
    }
}
