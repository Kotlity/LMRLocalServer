package com.handbook.lmr.database.table

import com.handbook.lmr.constants.DataConstants.AGE_COLUMN_NAME
import com.handbook.lmr.constants.DataConstants.CHARACTER_AGE
import com.handbook.lmr.constants.DataConstants.DATABASE_TABLE_NAME
import com.handbook.lmr.constants.DataConstants.DESCRIPTION_COLUMN_NAME
import com.handbook.lmr.constants.DataConstants.DESCRIPTION_MAX_LENGTH
import com.handbook.lmr.constants.DataConstants.FIRSTNAME_COLUMN_NAME
import com.handbook.lmr.constants.DataConstants.FIRST_LAST_NAME_MAX_LENGTH
import com.handbook.lmr.constants.DataConstants.ID_COLUMN_NAME
import com.handbook.lmr.constants.DataConstants.IMAGE_URL_COLUMN_NAME
import com.handbook.lmr.constants.DataConstants.IMAGE_URL_MAX_LENGTH
import com.handbook.lmr.constants.DataConstants.IS_FAVORITE_COLUMN_NAME
import com.handbook.lmr.constants.DataConstants.LASTNAME_COLUMN_NAME
import org.jetbrains.exposed.sql.Table

object CharactersTable: Table(DATABASE_TABLE_NAME) {
    val id = integer(ID_COLUMN_NAME)
    val firstname = varchar(FIRSTNAME_COLUMN_NAME, FIRST_LAST_NAME_MAX_LENGTH)
    val lastname = varchar(LASTNAME_COLUMN_NAME, FIRST_LAST_NAME_MAX_LENGTH)
    val age = integer(AGE_COLUMN_NAME).default(CHARACTER_AGE)
    val description = varchar(DESCRIPTION_COLUMN_NAME, DESCRIPTION_MAX_LENGTH)
    val imageurl = varchar(IMAGE_URL_COLUMN_NAME, IMAGE_URL_MAX_LENGTH)
    val isfavorite = bool(IS_FAVORITE_COLUMN_NAME).default(false)

    override val primaryKey = PrimaryKey(id)
}