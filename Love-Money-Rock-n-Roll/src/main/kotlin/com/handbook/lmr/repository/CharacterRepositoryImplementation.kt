package com.handbook.lmr.repository

import com.handbook.lmr.database.table.CharactersTable
import com.handbook.lmr.models.Character
import com.handbook.lmr.utils.DatabaseUtils.addAllCharacters
import com.handbook.lmr.utils.DatabaseUtils.databaseQuery
import com.handbook.lmr.utils.DatabaseUtils.queryResultRowToCharacter
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class CharacterRepositoryImplementation: CharacterRepository {

    override fun insertAllCharacters() {
        transaction {
            CharactersTable.batchInsert(addAllCharacters()) { character ->
                this[CharactersTable.id] = character.id
                this[CharactersTable.firstname] = character.firstName
                this[CharactersTable.lastname] = character.lastName
                this[CharactersTable.age] = character.age
                this[CharactersTable.description] = character.description
                this[CharactersTable.imageurl] = character.imageUrl
                this[CharactersTable.isfavorite] = character.isFavorite
            }
        }
    }

    override suspend fun getAllCharacters(): List<Character?> = databaseQuery {
        CharactersTable.selectAll().map { queryResultRow -> queryResultRowToCharacter(queryResultRow) }
    }

    override suspend fun getCharacterById(characterId: Int): Character? = databaseQuery {
        CharactersTable.select { CharactersTable.id eq characterId }.map { queryResultRow -> queryResultRowToCharacter(queryResultRow) }.first()
    }

    override suspend fun searchCharactersByFirstLastName(firstLastName: String): List<Character?> = databaseQuery {
        val searchQuery = "%${firstLastName.lowercase()}%"
        CharactersTable.select { (CharactersTable.firstname.lowerCase() like searchQuery) or (CharactersTable.lastname.lowerCase() like searchQuery) }.map { queryResultRow -> queryResultRowToCharacter(queryResultRow) }
    }

    override suspend fun updateCharacterWithIsFavorite(characterId: Int, character: Character) {
        databaseQuery {
            CharactersTable.update({(CharactersTable.id eq characterId) and (CharactersTable.id eq character.id)}) { suitableCharacter ->
                suitableCharacter[isfavorite] = character.isFavorite
            }
        }
    }

    override suspend fun getAllFavoriteCharacters(): List<Character?> = databaseQuery {
        CharactersTable.select { CharactersTable.isfavorite eq true }.map { queryResultRow -> queryResultRowToCharacter(queryResultRow) }
    }
}