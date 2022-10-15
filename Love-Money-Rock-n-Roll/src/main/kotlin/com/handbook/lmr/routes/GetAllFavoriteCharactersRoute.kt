package com.handbook.lmr.routes

import com.handbook.lmr.constants.ErrorConstants.FAVORITE_CHARACTERS_LIST_IS_EMPTY
import com.handbook.lmr.constants.RoutesConstants.ALL_FAVORITE_CHARACTERS_ROUTE
import com.handbook.lmr.repository.CharacterRepository
import com.handbook.lmr.utils.DatabaseUtils.errorResponse
import com.handbook.lmr.utils.DatabaseUtils.isDatabaseQueryResultEmpty
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.getAllFavoriteCharactersRoute() {
    val characterRepository: CharacterRepository by inject()
    route(ALL_FAVORITE_CHARACTERS_ROUTE) {
        get {
            val allFavoriteCharacters = characterRepository.getAllFavoriteCharacters()
            if (isDatabaseQueryResultEmpty(allFavoriteCharacters)) errorResponse(FAVORITE_CHARACTERS_LIST_IS_EMPTY, HttpStatusCode.NoContent) else call.respond(HttpStatusCode.OK, allFavoriteCharacters)
        }
    }
}