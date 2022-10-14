package com.handbook.lmr.utils

import com.handbook.lmr.constants.CharactersInformationConstants.ageCatherine
import com.handbook.lmr.constants.CharactersInformationConstants.ageEllie
import com.handbook.lmr.constants.CharactersInformationConstants.ageHimitsu
import com.handbook.lmr.constants.CharactersInformationConstants.ageKagome
import com.handbook.lmr.constants.CharactersInformationConstants.ageKyosuke
import com.handbook.lmr.constants.CharactersInformationConstants.ageMichael
import com.handbook.lmr.constants.CharactersInformationConstants.ageNikolai
import com.handbook.lmr.constants.CharactersInformationConstants.descriptionCatherine
import com.handbook.lmr.constants.CharactersInformationConstants.descriptionEllie
import com.handbook.lmr.constants.CharactersInformationConstants.descriptionHimitsu
import com.handbook.lmr.constants.CharactersInformationConstants.descriptionKagome
import com.handbook.lmr.constants.CharactersInformationConstants.descriptionKyosuke
import com.handbook.lmr.constants.CharactersInformationConstants.descriptionMichael
import com.handbook.lmr.constants.CharactersInformationConstants.descriptionNikolai
import com.handbook.lmr.constants.CharactersInformationConstants.firstNameCatherine
import com.handbook.lmr.constants.CharactersInformationConstants.firstNameEllie
import com.handbook.lmr.constants.CharactersInformationConstants.firstNameHimitsu
import com.handbook.lmr.constants.CharactersInformationConstants.firstNameKagome
import com.handbook.lmr.constants.CharactersInformationConstants.firstNameKyosuke
import com.handbook.lmr.constants.CharactersInformationConstants.firstNameMichael
import com.handbook.lmr.constants.CharactersInformationConstants.firstNameNikolai
import com.handbook.lmr.constants.CharactersInformationConstants.idCatherine
import com.handbook.lmr.constants.CharactersInformationConstants.idEllie
import com.handbook.lmr.constants.CharactersInformationConstants.idHimitsu
import com.handbook.lmr.constants.CharactersInformationConstants.idKagome
import com.handbook.lmr.constants.CharactersInformationConstants.idKyosuke
import com.handbook.lmr.constants.CharactersInformationConstants.idMichael
import com.handbook.lmr.constants.CharactersInformationConstants.idNikolai
import com.handbook.lmr.constants.CharactersInformationConstants.imageUrlCatherine
import com.handbook.lmr.constants.CharactersInformationConstants.imageUrlEllie
import com.handbook.lmr.constants.CharactersInformationConstants.imageUrlHimitsu
import com.handbook.lmr.constants.CharactersInformationConstants.imageUrlKagome
import com.handbook.lmr.constants.CharactersInformationConstants.imageUrlKyosuke
import com.handbook.lmr.constants.CharactersInformationConstants.imageUrlMichael
import com.handbook.lmr.constants.CharactersInformationConstants.imageUrlNikolai
import com.handbook.lmr.constants.CharactersInformationConstants.lastNameCatherine
import com.handbook.lmr.constants.CharactersInformationConstants.lastNameEllie
import com.handbook.lmr.constants.CharactersInformationConstants.lastNameHimitsu
import com.handbook.lmr.constants.CharactersInformationConstants.lastNameKagome
import com.handbook.lmr.constants.CharactersInformationConstants.lastNameKyosuke
import com.handbook.lmr.constants.CharactersInformationConstants.lastNameMichael
import com.handbook.lmr.constants.CharactersInformationConstants.lastNameNikolai
import com.handbook.lmr.database.table.CharactersTable
import com.handbook.lmr.models.Character
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseUtils {

    fun addAllCharacters(): List<Character> = listOf(
        Character(
            id = idNikolai,
            firstName = firstNameNikolai,
            lastName = lastNameNikolai,
            age = ageNikolai,
            description = descriptionNikolai,
            imageUrl = imageUrlNikolai
        ),
        Character(
            id = idHimitsu,
            firstName = firstNameHimitsu,
            lastName = lastNameHimitsu,
            age = ageHimitsu,
            description = descriptionHimitsu,
            imageUrl = imageUrlHimitsu
        ),
        Character(
            id = idCatherine,
            firstName = firstNameCatherine,
            lastName = lastNameCatherine,
            age = ageCatherine,
            description = descriptionCatherine,
            imageUrl = imageUrlCatherine
        ),
        Character(
            id = idEllie,
            firstName = firstNameEllie,
            lastName = lastNameEllie,
            age = ageEllie,
            description = descriptionEllie,
            imageUrl = imageUrlEllie
        ),
        Character(
            id = idKagome,
            firstName = firstNameKagome,
            lastName = lastNameKagome,
            age = ageKagome,
            description = descriptionKagome,
            imageUrl = imageUrlKagome
        ),
        Character(
            id = idKyosuke,
            firstName = firstNameKyosuke,
            lastName = lastNameKyosuke,
            age = ageKyosuke,
            description = descriptionKyosuke,
            imageUrl = imageUrlKyosuke
        ),
        Character(
            id = idMichael,
            firstName = firstNameMichael,
            lastName = lastNameMichael,
            age = ageMichael,
            description = descriptionMichael,
            imageUrl = imageUrlMichael
        )
    )

    suspend fun <T> databaseQuery(query: () -> T): T = withContext(Dispatchers.IO) { transaction { query() } }

    fun isDatabaseQueryResultEmpty(content: List<Character?>): Boolean = content.isEmpty()

    suspend fun PipelineContext<Unit, ApplicationCall>.errorResponse(errorText: String, httpStatus: HttpStatusCode) {
        call.respondText(text = errorText, status = httpStatus)
    }

    fun queryResultRowToCharacter(queryResultRow: ResultRow?): Character? {
        return if (queryResultRow == null) null else Character(
            id = queryResultRow[CharactersTable.id],
            firstName = queryResultRow[CharactersTable.firstname],
            lastName = queryResultRow[CharactersTable.lastname],
            age = queryResultRow[CharactersTable.age],
            description = queryResultRow[CharactersTable.description],
            imageUrl = queryResultRow[CharactersTable.imageurl],
            isFavorite = queryResultRow[CharactersTable.isfavorite]
        )
    }

}