package com.shadowzilot.callcountry.database

import android.content.ContentValues
import android.database.Cursor
import android.database.CursorWrapper
import com.shadowzilot.callcountry.database.CountryDBSchema.CountryTable.Cols.CAPITAL_LEVEL
import com.shadowzilot.callcountry.database.CountryDBSchema.CountryTable.Cols.FLAG_LEVEL
import com.shadowzilot.callcountry.database.CountryDBSchema.CountryTable.Cols.ID
import com.shadowzilot.callcountry.database.CountryDBSchema.CountryTable.Cols.LEARN_STATE
import com.shadowzilot.callcountry.database.CountryDBSchema.CountryTable.Cols.NAME_LEVEL

class CountryCursorWrapper(cursor: Cursor?) : CursorWrapper(cursor) {
    fun getValue(): ContentValues {
        val Id = getInt(getColumnIndex(ID))
        val nameLevel = getInt(getColumnIndex(NAME_LEVEL))
        val capitalLevel = getInt(getColumnIndex(CAPITAL_LEVEL))
        val flagLevel = getInt(getColumnIndex(FLAG_LEVEL))
        val learnState = getInt(getColumnIndex(LEARN_STATE))
        val result = ContentValues()

        result.put(ID, Id)
        result.put(NAME_LEVEL, nameLevel)
        result.put(CAPITAL_LEVEL, capitalLevel)
        result.put(FLAG_LEVEL, flagLevel)
        result.put(LEARN_STATE, learnState)

        return result
    }
}