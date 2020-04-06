package com.example.routers

import com.example.api.users.UserApiService
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.delete
import io.ktor.locations.get
import io.ktor.locations.put
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route

fun Route.users(userApiService: UserApiService) {

    route("/users") {

        get {
            call.respond(userApiService.readAll())
        }

        post {

            call.respond(userApiService.insert(call.receive()))
        }

        get<IntId> { userId ->
            val user = userApiService.findById(userId.id)

            if (user == null)
                call.respond(HttpStatusCode.NotFound)
            else
                call.respond(user)
        }


        put<IntId> { userId ->

            val user = userApiService.update(userId.id, call.receive())

            if (user == null)
                call.respond(HttpStatusCode.NotFound)
            else
                call.respond(user)
        }

        delete<IntId> { userId ->
            val userDeleted = userApiService.delete(userId.id)

            if (userDeleted)
                call.respond(HttpStatusCode.NoContent)
            else
                call.respond(HttpStatusCode.NotFound)
        }
    }

}