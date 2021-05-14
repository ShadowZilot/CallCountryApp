package com.shadowzilot.callcountry.database

import android.content.Context
import android.os.Binder
import android.os.Bundle
import android.util.Log
import java.lang.Exception
import java.lang.IndexOutOfBoundsException
import java.lang.Math.abs

const val LENGTH_LIST = 15
const val LENGTH_OF_POOL = 20

const val NAME_TEST = "test_name"
const val CAPITAL_TEST = "test_capital"
const val FLAG_TEST = "test_flag"

const val COUNTRY_ID_ARGUMENT = "country_id_argument"
const val TEST_KIND_ARGUMENT = "test_kind_argument"

private const val LOG_TAG = "LearningList"

class LearningList() : Binder() {
    private lateinit var context: Context
    private val list: MutableList<Country> = mutableListOf()
    private var currentIndex = 0

    constructor(context: Context) : this() {
        this.context = context
        generateLearningList()
    }

    fun getNext(): Bundle {
        val args = Bundle()

        try {
            val country = this.list[currentIndex]
            currentIndex++
            val testKind = CountryLab.get(context).getCountryTestKind(country.countryID)

            args.putString(TEST_KIND_ARGUMENT, testKind)
            args.putInt(COUNTRY_ID_ARGUMENT, country.countryID)

            return args
        } catch (e: IndexOutOfBoundsException) {
            throw Exception("Test list is up!")
        }
    }

    private fun getWorseSortList(database: CountryLab,
                                 countriesList: List<Country>): MutableList<Country> {
        val result = mutableListOf<Country>()
        val frequentlyList = mutableListOf<MutableList<Country>>()
        for (i in (0..5)) {
            frequentlyList.add(mutableListOf())
        }

        for (item in countriesList) {
            val index = database.getAverageLevel(item.countryID).toInt()
            frequentlyList[(index + abs(MIN_LEVEL.toInt()))].add(item)
        }

        var i = frequentlyList.size - 1
        while (i >= 1) {
            for (item in frequentlyList[i]) {
                if (result.size == LENGTH_LIST) return result
                result.add(item)
            }
            i--
        }
        return result
    }

    private fun generateLearningList() {
        val countriesList = getListOfProcessCountries()
        val database = CountryLab.get(this.context)
        val learnedCountries = getLearnedCountries(database.mCountries)
        this.list.clear()

        if (countriesList.isEmpty()) {
            val allCountries = database.mCountries
            makeRandomCountriesLearning(allCountries, learnedCountries)
            val sortedList = getWorseSortList(database,
                getListOfProcessCountries() as MutableList<Country>
            )
            this.list.addAll(sortedList)

        } else {
            val sortedList = getWorseSortList(database, countriesList)
            val learnedSortedList = getWorseSortList(database, learnedCountries)
            this.list.addAll(sortedList)

            if (learnedSortedList.isEmpty() && learnedCountries.isNotEmpty()) {
                var randomCountry = learnedCountries.random()
                while (this.list.size < LENGTH_LIST) {
                    while (this.list.contains(randomCountry)) {
                        randomCountry = learnedCountries.random()
                    }
                    this.list.add(randomCountry)
                }
            } else {
                for (country in learnedSortedList) {
                    if (this.list.size == LENGTH_LIST) break

                    if (!this.list.contains(country)) {
                        this.list.add(country)
                    }
                }
            }
        }

        Log.d(LOG_TAG, "Test list size =${this.list.size}")
    }

    private fun makeRandomCountriesLearning(allCountries: List<Country>,
                                            learnedCountries: List<Country>) {
        val database = CountryLab.get(context)

        for (i in 0 until LENGTH_OF_POOL) {
            var randomCountry: Country = allCountries.random()

            while (this.list.contains(randomCountry)
                && learnedCountries.contains(randomCountry)) {
                randomCountry = allCountries.random()
            }
            database.makeLearningState(randomCountry.countryID)
        }
    }

    private fun getLearnedCountries(allCountries: List<Country>): List<Country> {
        val database = CountryLab.get(context)
        val result = mutableListOf<Country>()
        for (country in allCountries) {
            if (database.getLearningState(country.countryID) == LEARNED_STATE) {
                result.add(country)
            }
        }
        return result
    }

    private fun getListOfProcessCountries(): List<Country> {
        val countriesList = CountryLab.get(this.context).mCountries
        val database = CountryLab.get(this.context)

        val result = mutableListOf<Country>()

        for (county in countriesList) {
            val learnState = database.getLearningState(county.countryID)
            if (learnState == LEARNING_STATE) {
                result.add(county)
            }
        }

        return result
    }
}