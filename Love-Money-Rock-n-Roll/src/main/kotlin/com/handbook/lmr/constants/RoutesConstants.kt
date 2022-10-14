package com.handbook.lmr.constants

object RoutesConstants {

    const val BASE_URL = "http://localhost:8080"
    const val CHARACTERS_ROUTE = "/characters"
    const val GET_CHARACTER_ROUTE = "/get"
    const val GET_CHARACTER_BY_ID_QUERY = "/{id?}"
    const val CHARACTER_ID_PARAMETER = "id"
    const val SEARCH_CHARACTERS_ROUTE = "/search"
    const val SEARCH_CHARACTERS_QUERY = "/{name?}"
    const val SEARCH_CHARACTERS_PARAMETER = "name"
    const val UPDATE_CHARACTER_ROUTE = "/update"
    const val UPDATE_CHARACTER_QUERY = "/{id?}"
}