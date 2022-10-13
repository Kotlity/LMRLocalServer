package com.handbook.lmr.database.factory

import com.handbook.lmr.constants.DataConstants.HIKARI_CONFIGURATION_FILE_DIR
import com.handbook.lmr.database.table.CharactersTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object CharacterDatabaseFactory {

    fun initDB() {
        Database.connect(hikari())
        transaction {
            SchemaUtils.create(CharactersTable)
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig(HIKARI_CONFIGURATION_FILE_DIR)
        return HikariDataSource(config)
    }

}