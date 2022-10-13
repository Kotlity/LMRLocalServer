package com.handbook.lmr

import com.handbook.lmr.database.factory.CharacterDatabaseFactory.initDB
import io.ktor.server.application.*
import com.handbook.lmr.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    initDB()
    configureSerialization()
    configureMonitoring()
    configureRouting()
}
