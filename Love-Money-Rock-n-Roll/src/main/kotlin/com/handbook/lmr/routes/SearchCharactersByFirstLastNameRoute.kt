package com.handbook.lmr.routes

import com.handbook.lmr.constants.ErrorConstants.DOES_NOT_MATCH_NAME
import com.handbook.lmr.constants.ErrorConstants.IS_NOT_SPECIFIED_NAME
import com.handbook.lmr.constants.RoutesConstants.SEARCH_CHARACTERS_PARAMETER
import com.handbook.lmr.constants.RoutesConstants.SEARCH_CHARACTERS_QUERY
import com.handbook.lmr.constants.RoutesConstants.SEARCH_CHARACTERS_ROUTE
import com.handbook.lmr.repository.CharacterRepository
import com.handbook.lmr.utils.DatabaseUtils.errorResponse
import com.handbook.lmr.utils.DatabaseUtils.isDatabaseQueryResultEmpty
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.searchCharactersByFirstLastName(characterRepository: CharacterRepository) {
    route(SEARCH_CHARACTERS_ROUTE) {
        get(SEARCH_CHARACTERS_QUERY) {
            val searchQuery = call.parameters[SEARCH_CHARACTERS_PARAMETER] ?: return@get errorResponse(IS_NOT_SPECIFIED_NAME, HttpStatusCode.BadRequest)
            val charactersByFirstLastName = characterRepository.searchCharactersByFirstLastName(searchQuery)
            if (isDatabaseQueryResultEmpty(charactersByFirstLastName)) errorResponse(DOES_NOT_MATCH_NAME, HttpStatusCode.NotFound) else call.respond(HttpStatusCode.OK, charactersByFirstLastName)
        }
    }
}