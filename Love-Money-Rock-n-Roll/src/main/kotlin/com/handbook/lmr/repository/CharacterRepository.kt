package com.handbook.lmr.repository

import com.handbook.lmr.models.Character

interface CharacterRepository {

    fun insertAllCharacters()

    suspend fun getAllCharacters(): List<Character?>

    suspend fun getCharacterById(characterId: Int): Character?

    suspend fun searchCharactersByFirstLastName(firstLastName: String): List<Character?>

    suspend fun updateCharacterWithIsFavorite(characterId: Int, character: Character)

    suspend fun getAllFavoriteCharacters(): List<Character?>
}