package com.handbook.lmr.models

import com.handbook.lmr.constants.DataConstants.CHARACTER_AGE
import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: Int,
    val firstNam: String,
    val lastName: String,
    val age: Int = CHARACTER_AGE,
    val description: String,
    val imageUrl: String,
    val isFavorite: Boolean = false
)
