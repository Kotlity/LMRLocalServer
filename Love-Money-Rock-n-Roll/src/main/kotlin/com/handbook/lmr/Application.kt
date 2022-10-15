package com.handbook.lmr

import com.handbook.lmr.database.factory.CharacterDatabaseFactory.initDB
import io.ktor.server.application.*
import com.handbook.lmr.plugins.*
import com.handbook.lmr.repository.CharacterRepository
import org.koin.ktor.ext.inject

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    val characterRepository: CharacterRepository by inject()
    configureKoin()
    initDB(characterRepository)
    configureSerialization()
    configureMonitoring()
    configureRouting()
}
