package com.handbook.lmr.routes

import com.handbook.lmr.constants.ErrorConstants.DOES_NOT_MATCH_ID
import com.handbook.lmr.constants.ErrorConstants.IS_NOT_SPECIFIED_ID
import com.handbook.lmr.constants.RoutesConstants.CHARACTER_ID_PARAMETER
import com.handbook.lmr.constants.RoutesConstants.GET_CHARACTER_BY_ID_QUERY
import com.handbook.lmr.constants.RoutesConstants.GET_CHARACTER_ROUTE
import com.handbook.lmr.repository.CharacterRepository
import com.handbook.lmr.utils.DatabaseUtils.errorResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getCharacterById(characterRepository: CharacterRepository) {
    route(GET_CHARACTER_ROUTE) {
        get(GET_CHARACTER_BY_ID_QUERY) {
            val characterId = call.parameters[CHARACTER_ID_PARAMETER]?.toInt() ?: return@get errorResponse(IS_NOT_SPECIFIED_ID, HttpStatusCode.BadRequest)
            val characterById = characterRepository.getCharacterById(characterId)
            if (characterById == null) errorResponse(DOES_NOT_MATCH_ID, HttpStatusCode.BadRequest) else call.respond(HttpStatusCode.OK, characterById)
        }
    }
}