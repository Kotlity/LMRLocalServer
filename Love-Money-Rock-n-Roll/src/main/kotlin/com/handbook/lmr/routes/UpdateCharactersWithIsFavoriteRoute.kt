package com.handbook.lmr.routes

import com.handbook.lmr.constants.ErrorConstants.IS_NOT_SPECIFIED_ID
import com.handbook.lmr.constants.ErrorConstants.WRONG_CHARACTER_ID_REQUEST
import com.handbook.lmr.constants.RoutesConstants.CHARACTER_ID_PARAMETER
import com.handbook.lmr.constants.RoutesConstants.UPDATE_CHARACTER_QUERY
import com.handbook.lmr.constants.RoutesConstants.UPDATE_CHARACTER_ROUTE
import com.handbook.lmr.models.Character
import com.handbook.lmr.repository.CharacterRepository
import com.handbook.lmr.utils.DatabaseUtils.errorResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.updateCharactersWithIsFavorite() {
    val characterRepository: CharacterRepository by inject()
    route(UPDATE_CHARACTER_ROUTE) {
        put(UPDATE_CHARACTER_QUERY) {
            val characterId = call.parameters[CHARACTER_ID_PARAMETER]?.toInt() ?: return@put errorResponse(IS_NOT_SPECIFIED_ID, HttpStatusCode.BadRequest)
            val characterBody = call.receive<Character>()
            if (characterId != characterBody.id) errorResponse(WRONG_CHARACTER_ID_REQUEST, HttpStatusCode.Conflict)
            else call.respond(HttpStatusCode.OK, characterRepository.updateCharacterWithIsFavorite(characterId, characterBody))
        }
    }
}