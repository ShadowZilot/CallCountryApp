package com.shadowzilot.callcountry.database

import android.content.ContentValues
import android.content.Context
import android.database.CursorWindow
import android.database.CursorWrapper
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.shadowzilot.callcountry.database.CountryDBSchema.CountryTable.Cols
import com.shadowzilot.callcountry.database.CountryDBSchema.CountryTable.NAME

private const val VERSION = 1
private const val DATABASE_NAME = "country_database.db"
private const val LOG_TAG = "CountryBaseHelper"

const val LEARNING_STATE = -1
const val LEARNED_STATE = 1
const val NEUTRAL_STATE = 0

class CountryBaseHelper(context: Context) : SQLiteOpenHelper(context,
        DATABASE_NAME, null, VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE " + NAME + "(" +
                "${Cols.ID} INTEGER PRIMARY KEY," +
                "${Cols.NAME_LEVEL} INTEGER, " +
                "${Cols.CAPITAL_LEVEL} INTEGER, "  +
                "${Cols.FLAG_LEVEL} INTEGER," +
                " ${Cols.LEARN_STATE} INTEGER)")
        populateDatabase(db)
    }

    private fun populateDatabase(db: SQLiteDatabase?) {
        var value: ContentValues

        for (i in 0 until 210) {
            value = ContentValues()
            value.put(Cols.ID, i)
            value.put(Cols.NAME_LEVEL, 0)
            value.put(Cols.CAPITAL_LEVEL, 0)
            value.put(Cols.FLAG_LEVEL, 0)
            value.put(Cols.LEARN_STATE, NEUTRAL_STATE)

            Log.d(LOG_TAG, db?.insert(NAME, null, value).toString())
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}