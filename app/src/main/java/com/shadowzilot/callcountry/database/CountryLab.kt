package com.shadowzilot.callcountry.database

import android.content.ContentValues
import android.content.Context
import android.database.CursorWrapper
import android.database.sqlite.SQLiteDatabase
import android.graphics.ColorSpace
import android.util.Log
import com.shadowzilot.callcountry.R
import com.shadowzilot.callcountry.database.CountryDBSchema.CountryTable
import com.shadowzilot.callcountry.database.CountryDBSchema.CountryTable.Cols
import org.json.JSONObject
import java.util.*
import java.util.function.LongFunction
import kotlin.collections.ArrayList

private const val LOG_TAG = "CountryLab"

// MIN and MAX value of country level
const val MIN_LEVEL = -2f
const val MAX_LEVEL = 3

class CountryLab {

    private var mContext: Context
    var mCountries: MutableList<Country>
    private var mDatabase: SQLiteDatabase

    private constructor(context: Context) {
        mContext = context.applicationContext
        mDatabase = CountryBaseHelper(mContext).writableDatabase
        mCountries = mutableListOf()

        val root = JSONObject(loadJsonString())
        val country = root.getJSONArray("country")
        var countryID = 0
        for (i in 0 until country.length()) {
            val jsonObject = country.getJSONObject(i)

            val countryName = jsonObject.getString("countryName")
            val countryCapital = jsonObject.getString("capitalName")
            val countryFlag = jsonObject.getString("flagName")
            val worldPart = jsonObject.getInt("worldPartCode")
            val population = jsonObject.getString("population")
            val square = jsonObject.getString("square")
            val language = jsonObject.getString("language")
            val currency = jsonObject.getString("currency")
            val formOfGovernment = jsonObject.getString("formOfGovernment")
            val religion = jsonObject.getString("religion")

            val country = Country(
                    countryID, countryName,
                    countryCapital, countryFlag, worldPart,
                    population, square, language, currency,
                    formOfGovernment, religion, mContext)

            Log.i(LOG_TAG, country.toString())
            countryID++

            mCountries.add(country)
        }
        mCountries = mCountries.sorted().toMutableList()
        Log.d(LOG_TAG, "Database is initialized")
        Log.d(LOG_TAG, "Size is ${mCountries.size}")
    }

    fun getLogList() {
        val cursor = mDatabase.query(
                CountryTable.NAME,
                null,
                null,
                null,
                null,
                null,
                null)
        cursor.moveToFirst()

        while (!cursor.isLast) {
            val value = CountryCursorWrapper(cursor).getValue()
            Log.d(LOG_TAG, "ID:${value.getAsInteger(Cols.ID)}" +
                    "  ${value.getAsInteger(Cols.NAME_LEVEL)}" +
                    " ${value.getAsInteger(Cols.CAPITAL_LEVEL)}")
            cursor.moveToNext()
        }
        cursor.close()
    }

    fun getCountryById(id: Int): Country {
        for (country in mCountries) {
            if (country.countryID == id) {
                return country
            }
        }
        return mCountries.random()
    }

    fun getAverageLevel(id: Int): Float {
        return (getCountryLevel(id, Cols.NAME_LEVEL) +
                getCountryLevel(id, Cols.CAPITAL_LEVEL) +
                getCountryLevel(id, Cols.FLAG_LEVEL)) / 3f
    }

    fun getCountryTestKind(id: Int): String {
        fun isAllEqual(list: List<Int>): Boolean {
            val result = true
            val first = list[0]
            for (item in list) {
                if (item != first) {
                    return !result
                }
            }
            return result
        }
        val list = listOf(getCountryLevel(id, Cols.NAME_LEVEL),
                getCountryLevel(id, Cols.CAPITAL_LEVEL),
                getCountryLevel(id, Cols.FLAG_LEVEL))

        var kind = if (isAllEqual(list)) -1 else 0

        var maxLevel = list[0]
        for (i in 1 until list.size) {
            if (list[i] > maxLevel) {
                maxLevel = list[i]
                kind = i
            }
        }

        return when (kind) {
            0 -> NAME_TEST
            1 -> CAPITAL_TEST
            2 -> FLAG_TEST
            else -> listOf(NAME_TEST, CAPITAL_TEST, FLAG_TEST).random()
        }
    }

    private fun getCountryLevel(id: Int, levelKind: String): Int {
        val value = getValueById(id)
        return value.getAsInteger(levelKind)
    }

    fun changeCountryLevel(id: Int, levelKind: String, different: Int) {
        val value = getValueById(id)
        val newLevel = checkLevelBounds(
                value.getAsInteger(levelKind) + different
        )

        val newValue = ContentValues()
        val listKinds = listOf(Cols.NAME_LEVEL, Cols.CAPITAL_LEVEL, Cols.FLAG_LEVEL)
        newValue.put(Cols.ID, value.getAsInteger(Cols.ID))
        for (kind in listKinds) {
            if (kind == levelKind) {
                newValue.put(kind, newLevel)
            } else {
                newValue.put(kind, value.getAsInteger(kind))
            }
        }

        updateCountry(newValue)
        if (getAverageLevel(id) == MIN_LEVEL) {
            newValue.put(Cols.LEARN_STATE, LEARNED_STATE)
            updateCountry(newValue)
        }
    }

    fun getLearningState(id: Int): Int = getValueById(id).getAsInteger(Cols.LEARN_STATE)

    fun makeLearningState(id: Int) {
        val newValue = getValueById(id)
        if (getLearningState(id) != LEARNED_STATE) {
            newValue.put(Cols.LEARN_STATE, LEARNING_STATE)
            updateCountry(newValue)
        }
    }

    private fun checkLevelBounds(value: Int): Int {
        return when {
            value > MAX_LEVEL -> MAX_LEVEL
            value < MIN_LEVEL -> MIN_LEVEL.toInt()
            else -> value
        }
    }

    private fun updateCountry(value: ContentValues) {
        val id = value.getAsInteger(Cols.ID)
        val whereArgs = arrayOf(id.toString())
        val updatedIndex = mDatabase.update(
                CountryTable.NAME,
                value,
                "${Cols.ID} = ?",
                whereArgs
        )
        Log.d(LOG_TAG, updatedIndex.toString())
        Log.d(LOG_TAG, "Updated $id")
    }

    fun getLearnedPercent(): Float {
        val max = mCountries.size * Math.abs(MIN_LEVEL)
        var sumFactor = 0f
        for (country in mCountries) {
            if (getAverageLevel(country.countryID) < 0) {
                sumFactor += Math.abs(getAverageLevel(country.countryID))
            }
        }

        return sumFactor / max
    }

    fun getLearnedCountriesList(): ArrayList<Country> {
        val result = ArrayList<Country>();
        for (i in 0 until mCountries.size) {
            if (getLearningState(i) == LEARNED_STATE) {
                result.add(getCountryById(i))
            }
        }
        return result
    }

    fun getAmountOfLearnedCountries(): Int {
        var amount = 0

        for (i in 0 until mCountries.size) {
            if (getLearningState(i) == LEARNED_STATE) {
                amount++
            }
        }

        return amount
    }

    private fun getValueById(id: Int): ContentValues {
        val result: ContentValues
        val cursor = mDatabase.query(
                CountryTable.NAME,
                null,
                "${Cols.ID} = ?",
                arrayOf(id.toString()),
                null,
                null,
                null)
        cursor.moveToFirst()
        result = CountryCursorWrapper(cursor).getValue()
        cursor.close()
        return result
    }

    private fun loadJsonString(): String {
        val stream = mContext.resources.openRawResource(R.raw.country_database)
        val scanner = Scanner(stream)
        val builder = StringBuilder()

        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine())
        }

        return builder.toString()
    }

    companion object {
        private var INSTANCE: CountryLab? = null

        fun get(context: Context): CountryLab {
            if (INSTANCE == null) {
                INSTANCE = CountryLab(context)
            }

            return INSTANCE!!
        }
    }
}