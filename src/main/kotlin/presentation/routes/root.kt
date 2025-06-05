package com.maulik.presentation.routes

import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.root() {
    get("/"){
        call.respondText("Hello This is Ktor")
    }
}