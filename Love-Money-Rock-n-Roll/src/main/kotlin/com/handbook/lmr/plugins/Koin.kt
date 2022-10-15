package com.handbook.lmr.plugins

import com.handbook.lmr.di.modules.LMRModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(LMRModule)
    }
}