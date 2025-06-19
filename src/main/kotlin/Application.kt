package com.maulik

import com.maulik.presentation.config.configureLogging
import com.maulik.presentation.config.configureRouting
import com.maulik.presentation.config.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    configureLogging()
}
