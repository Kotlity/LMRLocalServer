package com.handbook.lmr.utils

import com.handbook.lmr.constants.CharactersInformationConstant.ageCatherine
import com.handbook.lmr.constants.CharactersInformationConstant.ageEllie
import com.handbook.lmr.constants.CharactersInformationConstant.ageHimitsu
import com.handbook.lmr.constants.CharactersInformationConstant.ageKagome
import com.handbook.lmr.constants.CharactersInformationConstant.ageKyosuke
import com.handbook.lmr.constants.CharactersInformationConstant.ageMichael
import com.handbook.lmr.constants.CharactersInformationConstant.ageNikolai
import com.handbook.lmr.constants.CharactersInformationConstant.descriptionCatherine
import com.handbook.lmr.constants.CharactersInformationConstant.descriptionEllie
import com.handbook.lmr.constants.CharactersInformationConstant.descriptionHimitsu
import com.handbook.lmr.constants.CharactersInformationConstant.descriptionKagome
import com.handbook.lmr.constants.CharactersInformationConstant.descriptionKyosuke
import com.handbook.lmr.constants.CharactersInformationConstant.descriptionMichael
import com.handbook.lmr.constants.CharactersInformationConstant.descriptionNikolai
import com.handbook.lmr.constants.CharactersInformationConstant.firstNameCatherine
import com.handbook.lmr.constants.CharactersInformationConstant.firstNameEllie
import com.handbook.lmr.constants.CharactersInformationConstant.firstNameHimitsu
import com.handbook.lmr.constants.CharactersInformationConstant.firstNameKagome
import com.handbook.lmr.constants.CharactersInformationConstant.firstNameKyosuke
import com.handbook.lmr.constants.CharactersInformationConstant.firstNameMichael
import com.handbook.lmr.constants.CharactersInformationConstant.firstNameNikolai
import com.handbook.lmr.constants.CharactersInformationConstant.idCatherine
import com.handbook.lmr.constants.CharactersInformationConstant.idEllie
import com.handbook.lmr.constants.CharactersInformationConstant.idHimitsu
import com.handbook.lmr.constants.CharactersInformationConstant.idKagome
import com.handbook.lmr.constants.CharactersInformationConstant.idKyosuke
import com.handbook.lmr.constants.CharactersInformationConstant.idMichael
import com.handbook.lmr.constants.CharactersInformationConstant.idNikolai
import com.handbook.lmr.constants.CharactersInformationConstant.imageUrlCatherine
import com.handbook.lmr.constants.CharactersInformationConstant.imageUrlEllie
import com.handbook.lmr.constants.CharactersInformationConstant.imageUrlHimitsu
import com.handbook.lmr.constants.CharactersInformationConstant.imageUrlKagome
import com.handbook.lmr.constants.CharactersInformationConstant.imageUrlKyosuke
import com.handbook.lmr.constants.CharactersInformationConstant.imageUrlMichael
import com.handbook.lmr.constants.CharactersInformationConstant.imageUrlNikolai
import com.handbook.lmr.constants.CharactersInformationConstant.lastNameCatherine
import com.handbook.lmr.constants.CharactersInformationConstant.lastNameEllie
import com.handbook.lmr.constants.CharactersInformationConstant.lastNameHimitsu
import com.handbook.lmr.constants.CharactersInformationConstant.lastNameKagome
import com.handbook.lmr.constants.CharactersInformationConstant.lastNameKyosuke
import com.handbook.lmr.constants.CharactersInformationConstant.lastNameMichael
import com.handbook.lmr.constants.CharactersInformationConstant.lastNameNikolai
import com.handbook.lmr.database.table.CharactersTable
import com.handbook.lmr.models.Character
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