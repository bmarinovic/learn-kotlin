package com.example

import com.example.api.users.UserApiService
import com.example.api.users.UserApiServiceImpl
import com.example.core.config.Config
import com.example.domain.database.DbSettings
import com.example.domain.users.UserDomainServiceImpl
import com.example.routers.users
import com.sksamuel.hoplite.ConfigLoader
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.features.DataConversion
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.locations.Locations
import io.ktor.routing.routing
import io.ktor.serialization.json

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(Locations) {
    }

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost()
    }

    install(DataConversion)

    install(Authentication) {
    }

    install(ContentNegotiation) {
        json()
    }

    val config = ConfigLoader().loadConfigOrThrow<Config>("/application.conf")

    moduleWithDependencies(UserApiServiceImpl(UserDomainServiceImpl(DbSettings(config))))
}

fun Application.moduleWithDependencies(userApiService: UserApiService) {

    routing {
        users(userApiService)
    }
}