package com.example.routers

import com.example.api.users.User
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.Location
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

@Location("/users/{id}")
data class UserId(val id: Int)

fun Route.users() {

    route("/users") {

        get {
            call.respond(users)
        }

        post {
            users += call.receive<User>()

            call.respond(HttpStatusCode.Created)
        }
    }

    get<UserId> { userId ->
        call.respond(users[userId.id])
    }


    put<UserId> { userId ->
        users[userId.id] = call.receive()

        call.respond(users[userId.id])
    }

    delete<UserId> { userId ->
        users -= users[userId.id]

        call.respond(HttpStatusCode.NoContent)
    }
}

val users = Collections.synchronizedList(
    mutableListOf(
        User(1, "john", "123"),
        User(2, "jack", "555")
    )
)