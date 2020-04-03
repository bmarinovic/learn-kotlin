package com.example.routers

import com.example.api.users.User
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
import java.util.*

fun Route.users() {

    route("/users") {

        get {
            call.respond(users)
        }

        post {
            users += call.receive<User>()

            call.respond(HttpStatusCode.Created)
        }

        get<IntId> { userId ->
            call.respond(users[userId.id])
        }


        put<IntId> { userId ->
            users[userId.id] = call.receive()

            call.respond(users[userId.id])
        }

        delete<IntId> { userId ->
            users -= users[userId.id]

            call.respond(HttpStatusCode.NoContent)
        }
    }

}

val users = Collections.synchronizedList(
        mutableListOf(
                User(1, "john", "123"),
                User(2, "jack", "555")
        )
)